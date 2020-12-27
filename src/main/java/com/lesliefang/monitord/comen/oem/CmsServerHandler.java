package com.lesliefang.monitord.comen.oem;

import com.alibaba.fastjson.JSON;
import com.lesliefang.monitord.MqttClient;
import com.lesliefang.monitord.comen.oem.message.*;
import com.lesliefang.monitord.comen.oem.model.DeviceContext;
import com.lesliefang.monitord.comen.oem.model.DeviceEvent;
import com.lesliefang.monitord.comen.oem.model.EventType;
import com.lesliefang.monitord.comen.oem.model.VitalSign;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CmsServerHandler extends SimpleChannelInboundHandler<Packet> {
    private static final Logger logger = LoggerFactory.getLogger(CmsServer.class);
    private static ConcurrentHashMap<String, Channel> allChannels = new ConcurrentHashMap<>();
    // NetBedNum -> DeviceContext map, 设置时要保证网络床号的唯一性
    private static ConcurrentHashMap<Integer, DeviceContext> deviceContexts = new ConcurrentHashMap<>();
    private AttributeKey<String> snKey = AttributeKey.valueOf("sn");
    private AttributeKey<Integer> netBedNumKey = AttributeKey.valueOf("netBedNum");

    private VitalSign vitalSign = new VitalSign();

    private MqttClient mqttClient = new MqttClient();

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelActive {}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        DeviceContext deviceContext = deviceContexts.get((int) packet.getBedNum());
        if (deviceContext == null) {
            ctx.channel().attr(netBedNumKey).set((int) packet.getBedNum());
            deviceContext = new DeviceContext();
            deviceContext.setNetBedNum((int) packet.getBedNum());
            deviceContexts.put((int) packet.getBedNum(), deviceContext);
        }

        switch (packet.getType()) {
            case PacketType.PT_LOGIN:
                ctx.channel().writeAndFlush(new LoginAckPacket(0));
                break;
            case PacketType.PT_GETSYSTIME:
                handleGetSystemTimePacket(ctx.channel());
                break;
            case PacketType.PT_NET_CONTROL:
//                ctx.channel().writeAndFlush(new NetControlAckPacket());
                break;
            case PacketType.PT_DEVICE_SN:
                handleDeviceSnPacket((DeviceSnPacket) packet, ctx.channel(), deviceContext);
                break;
            case PacketType.PT_HEARTBEAT:
                break;
            case PacketType.PT_PATIENT_INFO:
                handlePatientInfoPacket((PatientInfoPacket) packet, deviceContext);
                // 只要收到 PATIENT_INFO 包就认为已经接收了病人
                deviceContext.setHasReceivePatient(true);
                break;
            case PacketType.PT_RECIEVE_PATIENT:
                // 监护仪接收病人
                deviceContext.setHasReceivePatient(true);
                break;
            case PacketType.PT_RELEASE_PATIENT:
                // 监护仪解除病人
                deviceContext.setHasReceivePatient(false);
                break;
            case PacketType.PT_HR_LIMIT_DATA:
                handleHRLimitPacket((HRLimitPacket) packet, deviceContext);
                break;
            case PacketType.PT_PR_LIMIT_DATA:
                handlePRLimitPacket((PRLimitPacket) packet, deviceContext);
                break;
            case PacketType.PT_RESP_LIMIT_DATA:
                handleRESPLimitPacket((RESPLimitPacket) packet, deviceContext);
                break;
            case PacketType.PT_SPO2_LIMIT_DATA:
                handleSPO2LimitPacket((SPO2LimitPacket) packet, deviceContext);
                break;
            case PacketType.PT_NIBP_LIMIT_DATA:
                handleNIBPLimitPacket((NIBPLimitPacket) packet, deviceContext);
                break;
            case PacketType.PT_TEMP_LIMIT_DATA:
                handleTEMPLimitPacket((TEMPLimitPacket) packet, deviceContext);
                break;
            case PacketType.PT_ECG1L_DATA:
                handleECG1Packet((ECG1LPacket) packet);
                break;
            case PacketType.PT_SPO2_DIG_DATA:
                handleSPO2DiaPacket((SPO2DigPacket) packet);
                break;
            case PacketType.PT_PR_DATA:
                handlePRPacket((PRPacket) packet);
                break;
            case PacketType.PT_RESP_DATA:
                handleRESPPacket((RESPPacket) packet);
                break;
            case PacketType.PT_NIBP_DATA:
                handleNIBPPacket((NIBPPacket) packet, deviceContext);
                break;
            case PacketType.PT_TEMP_DATA:
                handleTEMPPacket((TEMPPacket) packet);
                /*
                 * 接收到体温数据后发送生命体征数据给上位机，体温数据包是最后一个（依赖包的顺序）
                 */
                vitalSign.setHRAlarmLevel(deviceContext.getHRAlarmLevel());
                vitalSign.setHRHigh(deviceContext.getHRHighLimit());
                vitalSign.setHRLow(deviceContext.getHRLowLimit());
                vitalSign.setSPO2AlarmLevel(deviceContext.getSPO2AlarmLevel());
                vitalSign.setSPO2High(deviceContext.getSPO2HighLimit());
                vitalSign.setSPO2Low(deviceContext.getSPO2LowLimit());
                vitalSign.setRRAlarmLevel(deviceContext.getRESPAlarmLevel());
                vitalSign.setRRHigh(deviceContext.getRESPHighLimit());
                vitalSign.setRRLow(deviceContext.getRESPLowLimit());
                vitalSign.setPRAlarmLevel(deviceContext.getPRAlarmLevel());
                vitalSign.setPRHigh(deviceContext.getPRHighLimit());
                vitalSign.setPRLow(deviceContext.getPRLowLimit());
                vitalSign.setNIBPAlarmLevel(deviceContext.getNIBPAlarmLevel());
                vitalSign.setNIBP_SYS_High(deviceContext.getNIBPSysHighLimit());
                vitalSign.setNIBP_SYS_Low(deviceContext.getNIBPSysLowLimit());

                DeviceEvent deviceEvent = new DeviceEvent(EventType.EVENT_MONITOR_DATA, packet.getBedNum(), vitalSign);
                deviceEvent.setPatientName(deviceContext.getPatientName());
                publish(deviceEvent);
                break;
        }
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
        logger.info("channelInactive {}", ctx.channel().remoteAddress());
        String sn = ctx.channel().attr(snKey).get();
        Integer netBedNum = ctx.channel().attr(netBedNumKey).get();
        if (sn != null) {
            allChannels.remove(sn);
            ctx.channel().attr(snKey).set(null);
        }
        if (netBedNum != null) {
            deviceContexts.remove(netBedNum);
            ctx.channel().attr(netBedNumKey).set(null);
            publish(new DeviceEvent(EventType.EVENT_DISCONNECTED, netBedNum));
        }
        super.channelInactive(ctx);
    }

    private void handleGetSystemTimePacket(Channel channel) {
        Calendar calendar = Calendar.getInstance();
        channel.writeAndFlush(new SystemTimePacket((short) calendar.get(Calendar.YEAR),
                (byte) calendar.get(Calendar.MONTH), (byte) calendar.get(Calendar.DAY_OF_MONTH),
                (byte) calendar.get(Calendar.HOUR), (byte) calendar.get(Calendar.MINUTE),
                (byte) calendar.get(Calendar.SECOND)));
    }

    private void handleDeviceSnPacket(DeviceSnPacket packet, Channel channel, DeviceContext deviceContext) {
        channel.attr(snKey).set(packet.getSn());
        allChannels.put(packet.getSn(), channel);
        deviceContext.setDeviceSn(packet.getSn());
    }

    private void handlePatientInfoPacket(PatientInfoPacket packet, DeviceContext deviceContext) {
        deviceContext.setPatientName(packet.getName());
    }

    private void handleHRLimitPacket(HRLimitPacket packet, DeviceContext deviceContext) {
        deviceContext.setHRAlarmOn(packet.isAlarmOn());
        deviceContext.setHRAlarmLevel(packet.getAlarmLevel());
        deviceContext.setHRHighLimit(packet.getHigh());
        deviceContext.setHRLowLimit(packet.getLow());
    }

    private void handlePRLimitPacket(PRLimitPacket packet, DeviceContext deviceContext) {
        deviceContext.setPRAlarmOn(packet.isAlarmOn());
        deviceContext.setPRAlarmLevel(packet.getAlarmLevel());
        deviceContext.setPRHighLimit(packet.getHigh());
        deviceContext.setPRLowLimit(packet.getLow());
    }

    private void handleRESPLimitPacket(RESPLimitPacket packet, DeviceContext deviceContext) {
        deviceContext.setRESPAlarmOn(packet.isAlarmOn());
        deviceContext.setRESPAlarmLevel(packet.getAlarmLevel());
        deviceContext.setRESPHighLimit(packet.getHigh());
        deviceContext.setRESPLowLimit(packet.getLow());
    }

    private void handleSPO2LimitPacket(SPO2LimitPacket packet, DeviceContext deviceContext) {
        deviceContext.setSPO2AlarmOn(packet.isAlarmOn());
        deviceContext.setSPO2AlarmLevel(packet.getAlarmLevel());
        deviceContext.setSPO2HighLimit(packet.getHigh());
        deviceContext.setSPO2LowLimit(packet.getLow());
    }

    private void handleNIBPLimitPacket(NIBPLimitPacket packet, DeviceContext deviceContext) {
        deviceContext.setNIBPAlarmOn(packet.isAlarmOn());
        deviceContext.setNIBPAlarmLevel(packet.getAlarmLevel());
        deviceContext.setNIBPSysHighLimit(packet.getSysHigh());
        deviceContext.setNIBPSysLowLimit(packet.getSysLow());
        deviceContext.setNIBPDiaHighLimit(packet.getDiaHigh());
        deviceContext.setNIBPSysLowLimit(packet.getDiaLow());
        deviceContext.setNIBPMeanHighLimit(packet.getDiaHigh());
        deviceContext.setNIBPMeanLowLimit(packet.getMeanLow());
    }

    private void handleTEMPLimitPacket(TEMPLimitPacket packet, DeviceContext deviceContext) {
        deviceContext.setTEMPAlarmOn(packet.isAlarmOn());
        deviceContext.setTEMPAlarmLevel(packet.getAlarmLevel());
        deviceContext.setT1HighLimit(packet.getT1High());
        deviceContext.setT1LowLimit(packet.getT1Low());
        deviceContext.setT2HighLimit(packet.getT2High());
        deviceContext.setT2LowLimit(packet.getT2Low());
    }

    private void handleECG1Packet(ECG1LPacket packet) {
        vitalSign.setEcg1LeadOff(packet.isLeadOff());
        vitalSign.setEcg1LeadName(packet.getEcg1LeadName());
        vitalSign.setEcg1PVC(packet.getPVC());
        vitalSign.setHR(packet.getHR());
        vitalSign.setEcg1WaveData(packet.getWaveData());
    }

    private void handleSPO2DiaPacket(SPO2DigPacket packet) {
        vitalSign.setSPO2LeadOff(packet.isLeadOff());
        vitalSign.setSPO2(packet.getSPO2());
        vitalSign.setSpo2WaveData(packet.getWaveData());
    }

    private void handlePRPacket(PRPacket packet) {
        vitalSign.setPR(packet.getPR());
        vitalSign.setPRSource(packet.getSrc());
    }

    private void handleRESPPacket(RESPPacket packet) {
        vitalSign.setRRLeadOff(packet.isLeadOff());
        vitalSign.setRR(packet.getRR());
        vitalSign.setRespWaveData(packet.getWaveData());
    }

    private void handleTEMPPacket(TEMPPacket packet) {
        vitalSign.setTEMPLeadOff(packet.isLeadOff());
        vitalSign.setT1(packet.getT1());
        vitalSign.setT2(packet.getT2());
    }

    private void handleNIBPPacket(NIBPPacket packet, DeviceContext deviceContext) {
        vitalSign.setNIBP_TIME(packet.getTime());
        vitalSign.setNIBP_SYS(packet.getSys());
        vitalSign.setNIBP_DIA(packet.getDia());
        vitalSign.setNIBP_MEAN(packet.getMean());
        vitalSign.setNIBPFinish(packet.isFinish());
    }

    /**
     * 转发外部上位机请求到设备
     *
     * @param deviceSn 设备sn
     */
    public static void forwardToDevice(String deviceSn, Packet packet) {
        Channel channel = allChannels.get(deviceSn);
        if (channel == null) {
            logger.error("forwardToDevice->{} no channel", deviceSn);
            return;
        }
        channel.writeAndFlush(packet);
    }

    private void publish(DeviceEvent deviceEvent) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                mqttClient.publish("monitor/" + deviceEvent.getNetBedNum(), JSON.toJSONString(deviceEvent));
            }
        });
    }
}
