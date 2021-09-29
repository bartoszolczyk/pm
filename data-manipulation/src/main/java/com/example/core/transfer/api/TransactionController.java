package com.example.core.transfer.api;

import com.example.core.transfer.api.dto.TransactionDto;
import com.example.core.transfer.domain.TransferService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/transaction", produces = APPLICATION_JSON_VALUE)
public class TransactionController {

    private final TransferService transferService;

    @ApiOperation(value = "Perform transfer action of player between two teams and blling arounnd ")
    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.OK)
    public void createTeam(@Validated @NotNull @RequestBody TransactionDto transactionDto) {
        transferService.performPlayerTransaction(transactionDto);
    }

}
