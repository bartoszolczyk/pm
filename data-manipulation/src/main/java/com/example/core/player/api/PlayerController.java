package com.example.core.player.api;

import com.example.core.player.api.dto.PlayerDto;
import com.example.core.player.domain.PlayerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/accounts", produces = APPLICATION_JSON_VALUE)
public class PlayerController {

    private final PlayerService playerService;

    @ApiOperation(value = "Add new account draft ")
    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody PlayerDto accountDto) {

        playerService.addNewPlayer(accountDto);
    }

}
