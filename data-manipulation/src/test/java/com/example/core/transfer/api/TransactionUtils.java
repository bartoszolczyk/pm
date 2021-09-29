package com.example.core.transfer.api;

import com.example.core.transfer.api.dto.TransactionDto;

public interface TransactionUtils {

    static TransactionDto createPostTransaction() {

        return TransactionDto.builder()
            .playerId(2L)
            .originTeamiD(1L)
            .destinationTeamId(3L)
            .build();
    }
}
