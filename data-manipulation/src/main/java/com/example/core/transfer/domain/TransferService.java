package com.example.core.transfer.domain;

import com.example.commons.exception.OperationException;
import com.example.core.transfer.api.dto.TransactionDto;
import com.example.core.transfer.api.dto.TransferTransactionDto;
import com.example.data.model.Player;
import com.example.data.model.Team;
import com.example.data.model.TransferTransaction;
import com.example.data.repository.TeamRepository;
import com.example.data.repository.TransferTransactionRepository;
import com.example.gateways.currency.CurrencyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.example.commons.exception.messages.SystemExceptionMessage.NO_FOUNDS_ON_TEAM_ACCOUNT;
import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_ALREADY_TRANSFERRED;
import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_NOT_FOUND;
import static com.example.commons.exception.messages.SystemExceptionMessage.TEAM_NOT_FOUND;
import static java.math.RoundingMode.HALF_EVEN;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {

    private final CurrencyClient currencyClient;
    private final TeamRepository teamRepository;
    private final TransferTransactionRepository transactionRepository;
    private final TransferTransactionMapper  transactionMapper;


    private static final BigDecimal TRANSFER_CONSTANT = BigDecimal.valueOf(100000).setScale(2, HALF_EVEN);

    @Transactional
    public TransferTransactionDto performPlayerTransaction(TransactionDto transactionDto) {

        // get details
        final Team sellerTeam = getOwningTeam(transactionDto);
        final Player player = getPlayer(transactionDto, sellerTeam);
        final Team buyerTeam = getBuyerTeam(transactionDto);

        BigDecimal contractFee = getContractFee(sellerTeam, player);
        BigDecimal exchangeRate = currencyClient.getExchangeRate(sellerTeam.getCurrency(), buyerTeam.getCurrency());
        BigDecimal nativeCurrencyFee = contractFee.multiply(exchangeRate).setScale(2, HALF_EVEN);

        if (nativeCurrencyFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new OperationException(NO_FOUNDS_ON_TEAM_ACCOUNT, HttpStatus.METHOD_NOT_ALLOWED);
        }

        TransferTransaction transferTransaction = getTransaction(sellerTeam, player, buyerTeam, exchangeRate, nativeCurrencyFee);
        transactionRepository.save(transferTransaction);

        return transactionMapper.mapToTransferDto(transferTransaction) ;

    }

    private TransferTransaction getTransaction(Team sellerTeam, Player player, Team buyerTeam, BigDecimal exchangeRate, BigDecimal nativeCurrencyFee) {
        return TransferTransaction.builder()
            .player(player)
            .amount(nativeCurrencyFee)
            .buyer(buyerTeam)
            .seller(sellerTeam)
            .buyerCurrency(buyerTeam.getCurrency())
            .sellerCurrency(sellerTeam.getCurrency())
            .exchangeRate(exchangeRate)
            .build();
    }

    private Team getOwningTeam(TransactionDto transactionDto) {
        return teamRepository.findByIdAndAssignedPlayer(transactionDto.getOriginTeamiD(), transactionDto.getPlayerId())
            .orElseThrow(() -> new OperationException(TEAM_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    private Player getPlayer(TransactionDto transactionDto, Team owningTeam) {
        return owningTeam.getPlayers().stream().filter(p -> p.getId().equals(transactionDto.getPlayerId())).findFirst()
            .orElseThrow(() -> new OperationException(PLAYER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    private Team getBuyerTeam(TransactionDto transactionDto) {
        Team buyerTeam = teamRepository.findById(transactionDto.getDestinationTeamId())
            .orElseThrow(() -> new OperationException(TEAM_NOT_FOUND, HttpStatus.NOT_FOUND));
        if (buyerTeam.getPlayers().stream().anyMatch(p -> p.getId().equals(transactionDto.getPlayerId()))) {
            throw new OperationException(PLAYER_ALREADY_TRANSFERRED, HttpStatus.BAD_REQUEST);
        }
        return buyerTeam;
    }

    private BigDecimal getContractFee(Team owningTeam, Player player) {
        BigDecimal transferFee = getTransferFee(player);
        BigDecimal teamCommission = transferFee.multiply(owningTeam.getProvision()).setScale(2, HALF_EVEN);
        return teamCommission.add(transferFee).add(teamCommission).setScale(2, HALF_EVEN);
    }

    private BigDecimal getTransferFee(Player player) {
        return BigDecimal.valueOf(player.getMonthsOfExperience()).multiply(TRANSFER_CONSTANT).divide(BigDecimal.valueOf(player.getAge()),
            HALF_EVEN).setScale(2, HALF_EVEN);
    }
}
