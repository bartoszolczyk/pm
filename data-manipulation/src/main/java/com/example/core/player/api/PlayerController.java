package com.example.core.player.api;

import com.example.config.TimeProvider;
import com.example.core.player.api.dto.PlayerDto;
import com.example.core.player.api.dto.PlayerListDto;
import com.example.core.player.api.dto.TeamListDto;
import com.example.core.player.domain.PlayerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/player/v1", produces = APPLICATION_JSON_VALUE)
public class PlayerController {

    private final TimeProvider time;
    private final PlayerService playerService;

    @ApiOperation(value = "Add new player ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlayer(@Valid @NotNull @RequestBody PlayerDto playerDto) {

//        testClock //
        LocalDateTime now = LocalDateTime.now(time.getClock());
        log.info("currentTime is {}",now);
        playerService.addNewPlayer(playerDto);
    }

    @ApiOperation(value = "Update existing player ")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updatePlayer(@Valid @NotNull @RequestBody PlayerDto playerDto) {
        playerService.updatePlayer(playerDto);
    }

    @ApiOperation(value = "Get all players list ")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerListDto> getAllPlayers() {
        return playerService.listAllPlayers();
    }

    @ApiOperation(value = "Get given player teams ")
    @GetMapping("/{id}/team-details")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamListDto> getPlayerTeams(@Positive @PathVariable Long id) {
        return playerService.getPlayerTeams(id);
    }

    @ApiOperation(value = "Delete existing player ")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlayer(@Positive @PathVariable Long id) {
        playerService.deletePlayer(id);
    }

}
