package com.lesliefang.monitord.comen;

import com.alibaba.fastjson.JSON;
import com.lesliefang.monitord.comen.model.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMessageHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ServerMessageHandler.class);
    private AttributeKey<String> netBedNumKey = AttributeKey.valueOf("netBedNum");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = ((ByteBuf) msg);
        byte first = buf.readByte();
        if (first != 0x0B) {
            logger.error("not a HL7 low level message");
            return;
        }

        String hl7Message = buf.readCharSequence(buf.readableBytes(), CharsetUtil.ISO_8859_1).toString();
        String[] segments = hl7Message.split("\r"); // <CR> 回车符 0x0D

        DeviceData deviceData = new DeviceData();

        for (String segment : segments) {
//            logger.debug(segment);
            if (segment == null || segment.length() < 3) {
                logger.error("invalid segment " + segment);
                continue;
            }
            String segmentType = segment.substring(0, 3);
            if (HL7.MSH.equals(segmentType)) {
                // MSH|^~\&|22w|KC442440530G|||20201102225316+0800||ORU^R01|202011022253161371|P|2.4
                String[] fields = segment.split("\\|");
                if (fields.length >= 4) {
                    deviceData.setDeviceId(fields[3]);
                }
            } else if (HL7.PID.equals(segmentType)) {
                // PID|||987651234||zhang^san||1990-07-31|M
                String[] fields = segment.split("\\|");
                if (fields.length >= 6) {
                    deviceData.setPatientId(fields[3]);
                    deviceData.setPatientName(fields[5]);
                }
            } else if (HL7.PV1.equals(segmentType)) {
                // PV1||I|^^3-301&1
                String[] fields = segment.split("\\|");
                if (fields.length >= 4) {
                    String posInfo = fields[3];
                    // <>^<病房号>^<床号&网络床号>
                    String[] posCompos = posInfo.split("\\^");
                    if (posCompos.length >= 3) {
                        String bedInfo = posCompos[2];
                        String[] bedSubComps = bedInfo.split("&");
                        if (bedSubComps.length >= 2) {
                            String bedNum = bedSubComps[0];
                            String netBedNum = bedSubComps[1];
                            if (ctx.channel().attr(netBedNumKey).get() == null) {
                                ctx.channel().attr(netBedNumKey).set(netBedNum);
                            }
                            deviceData.setBedNum(bedNum);
                            deviceData.setNetBedNum(netBedNum);
                        }
                    }
                }
            } else if (HL7.OBR.equals(segmentType)) {
                // OBR|||||||20121113202904
                String[] fields = segment.split("\\|");
                if (fields.length >= 8) {
                    String datetime = fields[7];
                    if (datetime != null && datetime.length() > 14) {
                        datetime = datetime.substring(0, 14);
                    }
                    deviceData.setDatetime(datetime);
                }
            } else if (HL7.OBX.equals(segmentType)) {
                // OBX||NM|110^HR||80|bpm|60^130||||F
                // OBX||NM|115^PVC||0|bpm|0^0||||F
                // OBX||NM|112^ST1||4.72|mv|2.65^6.88||||F
                // OBX||NM|147842^MDC_ECG_HEART_RATE^MDC|1.7.4.147842|-100|^MDC_DIM_BEAT_PER_MIN|50^120||||F|||20201101211104+0800
                String[] fields = segment.split("\\|");
                if (fields.length >= 8) {
                    String codeFiled = fields[3];
                    String[] codeComps = codeFiled.split("\\^");
                    String code = null;
                    if (codeComps.length >= 2) {
                        code = codeComps[0];
                    }
                    String value = fields[5];
                    if ("-1".equals(value)
                            || "-100".equals(value) || "-10000".equals(value)) {
                        continue;
                    }

                    /*
                     * OBX||NM|150344^MDC_TEMP^MDC|1.2.1.150344|0.0|^MDC_DIM_DEGC|36.0^39.0||||F|||20201102225717+0800
                     * OBX||NM|150344^MDC_TEMP^MDC|1.2.2.150344|0.1|^MDC_DIM_DEGC|36.0^39.0||||F|||20201102225717+0800
                     * 根据子ID 区分 T1 和 T2
                     */
                    if ("150344".equals(code) && fields[4] != null) {
                        if ("1.2.1.150344".equals(fields[4])) {
                            code = code + "-T1";
                        } else if ("1.2.2.150344".equals(fields[4])) {
                            code = code + "-T2";
                        }
                    }

                    CodeTable codeTable = CodeTable.getCodeTable(code);
                    if (codeTable == null) {
                        // 只解析码表中存在的obx
                        continue;
                    }

                    Obx obx = new Obx();
                    obx.setKey(codeTable.getLabel());
                    obx.setValue(value);
                    obx.setUnit(codeTable.getUnit());
//                    String unit = fields[6];
//                    if (unit != null && unit.length() > 1 && unit.charAt(0) == '^') {
//                        unit = unit.substring(1);
//                    }
                    String[] lowHighLimitComps = fields[7].split("\\^");
                    String lowLimit = null, highLimit = null;
                    if (lowHighLimitComps.length >= 2) {
                        lowLimit = lowHighLimitComps[0];
                        highLimit = lowHighLimitComps[1];
                    }
                    if (lowLimit != null) {
                        obx.setLowLimit(Float.parseFloat(lowLimit));
                    }
                    if (highLimit != null) {
                        obx.setHighLimit(Float.parseFloat(highLimit));
                    }

                    deviceData.getObxMap().put(obx.getKey(), obx);
                }
            }
        }

        String netBedNum = ctx.channel().attr(netBedNumKey).get();
        if (netBedNum == null) {
            logger.error("no net bed num");
            return;
        }

        DeviceEvent deviceEvent = new DeviceEvent(netBedNum, EventCode.UPDATED.getCode());
        deviceEvent.setDeviceData(deviceData);
        publish(deviceEvent);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = ((IdleStateEvent) evt);
            if (e.state() == IdleState.READER_IDLE) {
                logger.info("read idle {} close channel", ctx.channel().remoteAddress());
                ctx.close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String netBedNum = ctx.channel().attr(netBedNumKey).get();
        if (netBedNum != null) {
            ctx.channel().attr(netBedNumKey).set(null);
            publish(new DeviceEvent(netBedNum, EventCode.DISCONNECTED.getCode()));
        }
    }

    private void publish(DeviceEvent deviceEvent) {
        System.out.println(JSON.toJSONString(deviceEvent));
    }
}
