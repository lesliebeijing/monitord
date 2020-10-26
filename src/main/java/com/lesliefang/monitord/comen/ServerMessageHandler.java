package com.lesliefang.monitord.comen;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMessageHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ServerMessageHandler.class);

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
        for (String segment : segments) {
            if (segment == null || segment.length() < 3) {
                continue;
            }
            String segmentType = segment.substring(0, 3);
            if (HL7.MSH.equals(segmentType)) {
                // MSH|^~\&|C80||||20121113202904||ORU^R01|201211132029049055|P|2.4
            } else if (HL7.PID.equals(segmentType)) {
                // PID|||987651234||zhang^san||1990-07-31|M
            } else if (HL7.PV1.equals(segmentType)) {
                // PV1||I|^^3-301&1
                String[] fields = segment.split("\\|");
                if (fields.length >= 4) {
                    String posInfo = fields[3];
                    // <>^<病房号>^<床号&网络床号>
                    String[] posArray = posInfo.split("^");
                    if (posArray.length >= 3) {
                        String bedInfo = posArray[2];
                    }
                }
            } else if (HL7.OBR.equals(segmentType)) {
                // OBR|||||||20121113202904
                String[] fields = segment.split("\\|");
                if (fields.length >= 8) {
                    String datetime = fields[7];
                }
            } else if (HL7.OBX.equals(segmentType)) {
                // OBX||NM|110^HR||80|bpm|60^130||||F
                // OBX||NM|115^PVC||0|bpm|0^0||||F
                // OBX||NM|112^ST1||4.72|mv|2.65^6.88||||F
                String[] fields = segment.split("\\|");
                if (fields.length >= 8) {
                    String codeComponent = fields[3];
                    String code = codeComponent.split("^")[0];
                    String key = codeComponent.split("^")[1];
                    String value = fields[5];
                    String unit = fields[6];
                    String minValue = fields[7].split("^")[0];
                    String maxValue = fields[7].split("^")[1];
                }
            }
        }
    }
}
