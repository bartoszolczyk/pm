package com.example.core.player.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.time.LocalDateTime;

@Value
@Builder
public class PlayerDto {

    Long id;

    @Positive
    @NotNull
    Integer age;

    @NotNull
    String name;

    @NotNull
    String surname;

    String team;

    @JsonIgnore
    @Builder.Default
    LocalDateTime updateDate = LocalDateTime.now();

}
