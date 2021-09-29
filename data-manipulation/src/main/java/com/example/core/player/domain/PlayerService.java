package com.example.core.player.domain;

import com.example.core.player.api.dto.PlayerDto;
import com.example.core.player.api.dto.PlayerListDto;
import com.example.core.player.api.dto.TeamListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerCreator playerCreator;
    private final PlayerUpdater playerUpdater;
    private final PlayerDeleter playerDeleter;
    private final PlayerGetter playerGetter;

    public void addNewPlayer(PlayerDto playerDto) {
        playerCreator.createPlayer(playerDto);
    }

    @Transactional
    public void updatePlayer(PlayerDto playerDto) {

        playerUpdater.updatePlayer(playerDto);
    }

    @Transactional
    public void deletePlayer(Long playerId) {
        playerDeleter.deletePlayer(playerId);
    }

    public List<PlayerListDto> listAllPlayers() {
        return  playerGetter.getAllPlayers();
    }

    public List<TeamListDto> getPlayerTeams(Long playerId) {
        return  playerGetter.getPlayerTeams(playerId);

    }
}
