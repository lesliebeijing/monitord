package com.lesliefang.monitord;

import com.lesliefang.monitord.comen.oem.CmsServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        new CmsServer().run();
    }
}
