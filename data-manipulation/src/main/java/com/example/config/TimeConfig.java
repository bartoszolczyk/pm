package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class TimeConfig {

    @Bean
    public TimeProvider clock() {
        return new TimeProvider(Clock.systemDefaultZone());
    }
}
