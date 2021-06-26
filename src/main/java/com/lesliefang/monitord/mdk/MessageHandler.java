package com.lesliefang.monitord.mdk;

import com.alibaba.fastjson.JSON;
import com.lesliefang.monitord.MqttClient;
import com.lesliefang.monitord.mdk.entity.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.ScheduledFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MessageHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    private ScheduledFuture<?> scheduledFuture;
    private MonitorEvent monitorEvent;
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static MqttClient mqttClient = new MqttClient();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelActive {}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
        scheduledFuture = ctx.channel().eventLoop().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ByteBuf buf = ctx.alloc().buffer();
                buf.writeBytes(new byte[]{0x02, 0x00, 0x00, 0x09});
                ctx.channel().writeAndFlush(buf);
                logger.debug("send 联网标志 " + System.currentTimeMillis() / 1000);
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelInactive {}", ctx.channel().remoteAddress());
        super.channelInactive(ctx);
        scheduledFuture.cancel(false);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf data = ((ByteBuf) msg);
//        System.out.println(ByteBufUtil.prettyHexDump(data));
        if (monitorEvent == null) {
            monitorEvent = new MonitorEvent();
        }
        try {
            int bedNum = data.getByte(2) & 0xff;
            int packetType = data.getByte(3);
            data.skipBytes(4); // 去掉前4个字节到达数据部分

            monitorEvent.bed = bedNum;

            if (packetType == PacketType.PATIENT) {
                // 病人信息
                parePatient(data);
            } else if (packetType == PacketType.MODULE_INFO) {
                // MODULE INFO
            } else if (packetType == PacketType.ECG) {
                // ECG
                ECG ecg = parseECG(data);

                monitorEvent.hr = ecg.hr;
                monitorEvent.hr_low = ecg.hr_low;
                monitorEvent.hr_high = ecg.hr_high;
                monitorEvent.hr_level = ecg.hr_level;

                monitorEvent.rr = ecg.rr;
                monitorEvent.rr_low = ecg.rr_low;
                monitorEvent.rr_high = ecg.rr_high;
                monitorEvent.rr_level = ecg.rr_level;

                byte[] wave1 = new byte[256];
                System.arraycopy(ecg.wavedata, 0, wave1, 0, 256);
                byte[] wave2 = new byte[256];
                System.arraycopy(ecg.wavedata, 256, wave2, 0, 256);
                byte[] respWave = new byte[128];
                System.arraycopy(ecg.wavedata, 256 + 256, respWave, 0, 128);

                monitorEvent.ecg_wave1 = wave1;
                monitorEvent.ecg_wave2 = wave2;
                monitorEvent.ecg_wave3 = ecg.ecg3data;
                monitorEvent.resp_wave = respWave;

            } else if (packetType == PacketType.SPO2) {
                // SPO2
                SPO2 spo2 = parseSPO2(data);

                monitorEvent.spo2 = spo2.spo2;
                monitorEvent.spo2_high = spo2.spo2_high;
                monitorEvent.spo2_low = spo2.spo2_low;
                monitorEvent.spo2_level = spo2.spo2_level;
                monitorEvent.spo2_wave = spo2.wavedata;

                monitorEvent.pr = spo2.pr;
                monitorEvent.pr_high = spo2.pr_high;
                monitorEvent.pr_low = spo2.pr_low;
                monitorEvent.pr_level = spo2.pr_level;
            } else if (packetType == PacketType.NIBP) {
                // NIBP
                NIBP nibp = parseNIBP(data);

                monitorEvent.nibp_sys = nibp.sys;
                monitorEvent.nibp_sys_high = nibp.sys_high;
                monitorEvent.nibp_sys_low = nibp.sys_low;
                monitorEvent.nibp_sys_level = nibp.sys_level;

                monitorEvent.nibp_dia = nibp.dia;
                monitorEvent.nibp_dia_high = nibp.dia_high;
                monitorEvent.nibp_dia_low = nibp.dia_low;
                monitorEvent.nibp_dia_level = nibp.dia_level;

                monitorEvent.nibp_mean = nibp.mea;
            } else if (packetType == PacketType.TEMP) {
                // 体温
                Temperature temp = parseTemp(data);

                monitorEvent.t1 = temp.t1;
                monitorEvent.t2 = temp.t2;
            } else if (packetType == PacketType.FETUS) {
                // 胎监
                parseFETUS(data);
            } else {
                logger.error("unknown packetType {}", packetType);
            }
        } finally {
            data.release();
        }

        publish(monitorEvent);
    }

    private Patient parePatient(ByteBuf data) {
        Patient patient = new Patient();
        //    BYTE name[24]; 病人信息
        //    BYTE medno[24] ;
        //    BYTE doctor[24] ; 医生信息
        //    BYTE age[6]; 年龄
        //    BYTE height[6];
        //    身高 BYTE weight[6]; 体重
        //    BYTE gender; BYTE bloodtype ;
        //    BYTE patienttype ;
        //    BYTE ommit;
        //    BYTE comment[24]; 备注，倒数第十个数据为床号
        patient.name = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        patient.medNo = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        patient.doctor = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        data.skipBytes(6); // 年龄
        data.skipBytes(6); // 身高
        data.skipBytes(6); // 体重
        patient.gender = data.readByte();
        patient.bloodType = data.readByte();
        patient.patientType = data.readByte();
        patient.ommit = data.readByte();
        data.skipBytes(24); // comment

        logger.debug("Patient: {}", patient);

        return patient;
    }

    /**
     * 报警级别是这样的： 0:高报警 1:中报警 2:低报警
     */
    private ECG parseECG(ByteBuf data) {
        ECG ecg = new ECG();
        ecg.ecglead1off = data.readByte();
        ecg.ecglead2off = data.readByte();
        ecg.leadmode = data.readByte();
        ecg.respleadoff = data.readByte();
        ecg.ecgdef1 = data.readShortLE();
        ecg.ecgdef2 = data.readShortLE();
        ecg.hr = data.readShortLE();
        ecg.hr_high = data.readShortLE();
        ecg.hr_low = data.readShortLE();
        ecg.hr_level = data.readShortLE();
        ecg.rr = data.readShortLE();
        ecg.rr_high = data.readShortLE();
        ecg.rr_low = data.readShortLE();
        ecg.rr_level = data.readShortLE();
        ecg.st1 = data.readShortLE();
        ecg.st1_high = data.readShortLE();
        ecg.st1_low = data.readShortLE();
        ecg.st1_level = data.readShortLE();
        ecg.st2 = data.readShortLE();
        ecg.st2_high = data.readShortLE();
        ecg.st2_low = data.readShortLE();
        ecg.st2_level = data.readShortLE();
        ecg.pvc = data.readShortLE();
        ecg.pvc_high = data.readShortLE();
        ecg.pvc_low = data.readShortLE();
        ecg.pvc_level = data.readShortLE();

        data.readBytes(ecg.wavedata);    //	2通道心电波形，1通道呼吸波形。
        data.readBytes(ecg.ecg3data);
        ecg.demoflag = data.readByte();
        ecg.ext = data.readByte();

        logger.debug("ECG : {}", ecg);

        return ecg;
    }

    private SPO2 parseSPO2(ByteBuf data) {
        SPO2 spo2 = new SPO2();
        data.skipBytes(4);
        spo2.spo2 = data.readShortLE();
        spo2.spo2_high = data.readShortLE();
        spo2.spo2_low = data.readShortLE();
        spo2.spo2_level = data.readShortLE();
        spo2.pr = data.readShortLE();
        spo2.pr_high = data.readShortLE();
        spo2.pr_low = data.readShortLE();
        spo2.pr_level = data.readShortLE();
        data.readBytes(spo2.wavedata); // 波形

        logger.debug("SPO2 : {}", spo2);

        return spo2;
    }

    private NIBP parseNIBP(ByteBuf data) {
        NIBP nibp = new NIBP();
        data.skipBytes(4);  // 将来扩展
        data.skipBytes(12); // 将来扩展
        nibp.sys = data.readShortLE(); // 高压值
        nibp.sys_high = data.readShortLE();
        nibp.sys_low = data.readShortLE();
        nibp.sys_level = data.readShortLE();
        nibp.mea = data.readShortLE(); // 平均压值
        nibp.mea_high = data.readShortLE();
        nibp.mea_low = data.readShortLE();
        nibp.mea_level = data.readShortLE();
        nibp.dia = data.readShortLE(); // 低压值
        nibp.dia_high = data.readShortLE();
        nibp.dia_low = data.readShortLE();
        nibp.dia_level = data.readShortLE();

        logger.debug("NIBP : {}", nibp);

        return nibp;
    }

    private Temperature parseTemp(ByteBuf data) {
        Temperature temp = new Temperature();
        data.skipBytes(4); // 将来扩展
        temp.t1 = data.readShortLE(); // 体温1值
        temp.t1_high = data.readShortLE();
        temp.t1_low = data.readShortLE();
        temp.t1_level = data.readShortLE();
        temp.t2 = data.readShortLE(); // 体温2值
        temp.t2_high = data.readShortLE();
        temp.t2_low = data.readShortLE();
        temp.t2_level = data.readShortLE();

        logger.debug("TEMP : {}", temp);

        return temp;
    }

    private FETUS parseFETUS(ByteBuf data) {
        FETUS fetus = new FETUS();
        data.skipBytes(4); // 将来扩展
        fetus.fetusmode = data.readByte();    // 分辨率
        fetus.fetusvolume = data.readByte(); //胎心音量
        fetus.marked = data.readByte(); // 胎动标记
        fetus.ucreset = data.readByte();// 宫缩复位标记

        // 胎心率值
        fetus.fhr[0] = data.readShortLE();
        fetus.fhr[1] = data.readShortLE();
        fetus.fhr[2] = data.readShortLE();
        fetus.fhr[3] = data.readShortLE();

        fetus.fhr_high = data.readShortLE();
        fetus.fhr_low = data.readShortLE();
        fetus.fhr_level = data.readShortLE();

        // 宫缩压值
        fetus.uc[0] = data.readShortLE();
        fetus.uc[1] = data.readShortLE();
        fetus.uc[2] = data.readShortLE();
        fetus.uc[3] = data.readShortLE();

        fetus.uc_high = data.readShortLE();
        fetus.uc_low = data.readShortLE();
        fetus.uc_level = data.readShortLE();

        logger.debug("胎监 : {}", fetus);

        return fetus;
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

    private void publish(MonitorEvent monitorEvent) {
        String message = JSON.toJSONString(monitorEvent);
        // 推送到 mqtt
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                mqttClient.publish("monitor/" + monitorEvent.bed, message);
            }
        });

//        // 推送到 websocket
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                WebSocketServer.publishToAll(message);
//            }
//        });
    }
}
