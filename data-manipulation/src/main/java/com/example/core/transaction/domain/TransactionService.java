package com.example.core.transaction.domain;

import com.example.core.transaction.api.dto.TransactionDto;
import com.example.data.model.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CurrencyComponent currencyComponent;
    private final TeamRepository teamRepository;

    @Transactional
    public void performPlayerTransaction(TransactionDto transactionDto) {
//        Optional<Team> sourceTeam =
//            teamRepository.findByIdAndAssignedPlayer(transactionDto.getOriginTeamiD(),transactionDto.getPlayerId()).orElseThrow(Exception::new) ;
//        Optional<Team> targetTeam = teamRepository.findTargetTeam(transactionDto.getDestinationTeamId(),transactionDto.getPlayerId()).orElseThrow(Exception::new) ; ;

    }
}
