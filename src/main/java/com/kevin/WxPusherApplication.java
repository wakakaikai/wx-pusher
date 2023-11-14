package com.kevin;

import io.github.asleepyfish.annotation.EnableChatGPT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableChatGPT
@EnableScheduling
@EnableFeignClients
@EnableJpaAuditing
public class WxPusherApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxPusherApplication.class, args);
    }

}
