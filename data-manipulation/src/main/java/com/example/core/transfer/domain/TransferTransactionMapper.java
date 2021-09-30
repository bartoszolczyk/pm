package com.example.core.transfer.domain;

import com.example.core.team.domain.TeamMapper;
import com.example.core.transfer.api.dto.TransferTransactionDto;
import com.example.data.model.TransferTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface TransferTransactionMapper {


    @Mapping(target = "sellerId", source = "seller.id")
    @Mapping(target = "playerId", source = "player.id")
    @Mapping(target = "buyerID", source = "buyer.id")
    TransferTransactionDto mapToTransferDto(TransferTransaction transferTransaction) ;

}
