package com.example.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfer_transaction")
public class TransferTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer-generator")
    @SequenceGenerator(name = "transfer-generator", sequenceName = "transfer_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player",
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "player_id_ts_fk"))
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller",
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "player_selling_ts_fk"))
    private Team seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer",
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "player_buying_ts_fk"))
    private Team buyer;

    @NotNull
    @Builder.Default
    @Column(name = "date_created", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "buyer_currency", nullable = false, length = 3)
    private Currency buyerCurrency;

    @Column(name = "seller_currency", nullable = false, length = 3)
    private Currency sellerCurrency;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "exchange_rate", nullable = false, precision = 20, scale = 5)
    private BigDecimal exchangeRate;

}


