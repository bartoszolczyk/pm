package com.example.core.player.api;

import com.example.core.player.api.dto.PlayerDto;
import com.example.core.player.domain.PlayerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/player/v1", produces = APPLICATION_JSON_VALUE)
public class PlayerController {

    private final PlayerService playerService;

    @ApiOperation(value = "Add new player ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlayer(@Validated @RequestBody PlayerDto playerDto) {
        playerService.addNewPlayer(playerDto);
    }

    @ApiOperation(value = "Update existing player ")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updatePlayer(@RequestBody PlayerDto playerDto) {
        playerService.updatePlayer(playerDto);
    }

    @ApiOperation(value = "Delete existing player ")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }

}
