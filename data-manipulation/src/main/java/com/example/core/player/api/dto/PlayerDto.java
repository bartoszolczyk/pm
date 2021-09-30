package com.example.core.player.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
@Jacksonized
public class PlayerDto {

    Long id;

    @NotNull
    String name;

    @NotNull
    String surname;

    @Positive
    @NotNull
    Integer age;

    @NotNull
    @PositiveOrZero
    Integer monthsOfExperience;

    @Builder.Default
    List<Long> teams = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    LocalDateTime updateDate = LocalDateTime.now();

}
