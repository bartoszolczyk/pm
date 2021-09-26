package com.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team-generator")
    @SequenceGenerator(name = "team-generator", sequenceName = "team_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = ALL, mappedBy = "team")
    private List<Player> players;

    @NotNull
    @Builder.Default
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "currency", nullable = false, length = 3)
    private Currency currency;
}