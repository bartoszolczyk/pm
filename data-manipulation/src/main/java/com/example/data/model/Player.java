package com.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player-generator")
    @SequenceGenerator(name = "player-generator", sequenceName = "player_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age", nullable = false)
    private Integer age;

    @PositiveOrZero
    @Builder.Default
    @Column(name = "months_of_experience", nullable = false)
    private Integer monthsOfExperience = 0;

    @ManyToMany
    @JoinTable(name = "player_assigment",
        joinColumns = @JoinColumn(name = "assgned_player_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_PLAYER_ASSIGMENT_PLAYER_ID")),
        inverseJoinColumns = @JoinColumn(name = "team_owner_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_PLAYER_ASSIGMENT_TEAM_ID"))
    )
    private Set<Team> playerTeams;

    @NotNull
    @Builder.Default
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "date_updated")
    private LocalDateTime updateDate;

    public void manageRelations() {
        Optional.ofNullable(playerTeams).ifPresent(teams -> teams.forEach(team -> team.addPlayer(this)));
    }
}
