package com.example.data.model.repository;

import com.example.data.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT t FROM Team t join Player p where t.id =  :originTeamiD and p.id = :playerId ")
    Optional<Team> findByIdAndAssignedPlayer(@Param("originTeamiD") Long originTeamiD, @Param("playerId") Long playerId);

    @Query(value = "SELECT t FROM Team t join Player p where t.id =  :destinationTeamId and p.id <> :playerId ")
    Optional<Team> findTargetTeam(@Param("destinationTeamId") Long destinationID, @Param("playerId") Long playerId);

}
