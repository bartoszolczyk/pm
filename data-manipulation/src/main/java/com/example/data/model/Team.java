package com.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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

    @ManyToMany(mappedBy = "playerTeams")
    Set<Player> players;

    @NotNull
    @Builder.Default
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDate creationDate = LocalDate.now();

    // TODO : set scale on save !!!!
    @Column(name = "currency", nullable = false, length = 3)
    private Currency currency;

    @Column(name = "provision", nullable = false, precision = 5, scale = 4)
    private BigDecimal provision;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @OneToMany(mappedBy = "seller")
    private List<TransferTransaction> sellingTransactions;

    @OneToMany(mappedBy = "buyer")
    private List<TransferTransaction> buyingTransactions;

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
