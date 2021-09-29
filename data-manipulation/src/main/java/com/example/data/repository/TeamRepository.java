package com.example.data.repository;

import com.example.data.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT t FROM Team t  JOIN t.players p  WHERE t.id = :teamId AND p.id = :playerId ")
    Optional<Team> findByIdAndAssignedPlayer(Long teamId, Long playerId);
}
