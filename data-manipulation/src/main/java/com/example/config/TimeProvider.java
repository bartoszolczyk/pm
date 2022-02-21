package com.example.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Clock;

@AllArgsConstructor
@Getter
public class TimeProvider {

    private Clock clock;
}
