package com.example.core.player.domain;

import com.example.commons.exception.OperationException;
import com.example.core.player.api.dto.PlayerListDto;
import com.example.core.player.api.dto.TeamListDto;
import com.example.core.team.domain.TeamMapper;
import com.example.data.model.Player;
import com.example.data.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_NOT_FOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlayerGetter {

    private final PlayerMapper playerMapper;
    private final TeamMapper teamMapper;

    private final PlayerRepository playerRepository;

    public List<PlayerListDto> getAllPlayers() {
        return playerMapper.toListOfPlayers(playerRepository.findAll());
    }

    public List<TeamListDto> getPlayerTeams(Long id) {

        Player player = playerRepository.findById(id).orElseThrow(() -> new OperationException(PLAYER_NOT_FOUND, HttpStatus.NOT_FOUND));
        return teamMapper.mapPlayerTeamsToDto(player.getPlayerTeams());
    }

}
