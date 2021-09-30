package com.example.core.transfer.domain;

import com.example.commons.exception.OperationException;
import com.example.core.transfer.api.dto.TransactionDto;
import com.example.core.transfer.api.dto.TransferTransactionDto;
import com.example.data.model.Player;
import com.example.data.model.Team;
import com.example.data.model.TransferTransaction;
import com.example.data.repository.TeamRepository;
import com.example.data.repository.TransferTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_ALREADY_TRANSFERRED;
import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_NOT_FOUND;
import static com.example.commons.exception.messages.SystemExceptionMessage.TEAM_NOT_FOUND;
import static com.example.commons.exception.messages.SystemExceptionMessage.TRANSFER_TRANSACTION_EXCEPTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferService {

    private final TeamRepository teamRepository;
    private final TransferTransactionRepository transactionRepository;
    private final TransferTransactionMapper transactionMapper;
    private final TransactionUtils transactionUtils;

    @Transactional
    public TransferTransactionDto performPlayerTransaction(TransactionDto transactionDto) {

        // get details
        TransferTransaction transferTransaction = null;
        try {
            final Team sellerTeam = getOwningTeam(transactionDto);
            final Player player = getPlayer(transactionDto, sellerTeam);
            final Team buyerTeam = getBuyerTeam(transactionDto);

            NativeCurrencyFee nativeCurrencyFee = transactionUtils.computeFeeInNativeCurrency(sellerTeam, buyerTeam, player);

            transferTransaction = getTransaction(sellerTeam, player, buyerTeam, nativeCurrencyFee.getExchangeRate(),
                nativeCurrencyFee.getNativeCurrencyFee());
            transactionRepository.save(transferTransaction);
        } catch (OperationException e) {
            throw e;
        } catch (Exception e) {
            throw new OperationException(TRANSFER_TRANSACTION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return transactionMapper.mapToTransferDto(transferTransaction);
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

}
