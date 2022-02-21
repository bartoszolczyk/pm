package com.example.core;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

//@Configuration
public class FakeClockConfig {

    private final static LocalDate WEEKEND = LocalDate.of(1999, 7, 5);

//    @Bean
    public Clock clock() {
        return Clock.fixed(WEEKEND.atTime(9, 5).toInstant(ZoneOffset.UTC), ZoneId.of("CET"));
    }
}