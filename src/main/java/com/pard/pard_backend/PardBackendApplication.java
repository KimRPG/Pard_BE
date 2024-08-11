package com.pard.pard_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //예약된 작업 수행하게 -> 매일 00:00에 일정 업데이트
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class PardBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PardBackendApplication.class, args);
    }

}
