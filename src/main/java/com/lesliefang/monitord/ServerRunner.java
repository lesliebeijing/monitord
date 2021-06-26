package com.lesliefang.monitord;

import com.lesliefang.monitord.mdk.MdkMonitorServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
//        new CmsServer().run(); // OEM server
//
//        new WebSocketServer().run(); // websocket server

        // 床位
        int[] bedList = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < bedList.length; i++) {
            new MdkMonitorServer(bedList[i]).run();
        }
    }
}
