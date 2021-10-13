package com.qds.ulinzi.monify.mapper;

import com.qds.ulinzi.entity.MonnifyAccount;
import com.qds.ulinzi.monify.dto.CustomerAccount;
import com.qds.ulinzi.service.dto.AccountDTO;
import com.qds.ulinzi.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface MonnifyAccountResponseMapper extends EntityMapper<CustomerAccount, MonnifyAccount> {

    @Override
    @Mapping(target = "type",constant = "MONNIFY")
    @Mapping(source = "accountName", target = "defaultAccountName")
    @Mapping(source = "accounts", target = "defaultBankName", qualifiedByName = "getBankName")
    @Mapping(source = "accounts", target = "defaultAccountNumber", qualifiedByName = "getAccountNumber")
    MonnifyAccount toEntity(CustomerAccount dto);

    @Override
    CustomerAccount toDto(MonnifyAccount entity);

    @Named("getBankName")
    default String getBankName(List<AccountDTO> accounts){
        return accounts.get(0).getBankName();
    }

    @Named("getAccountNumber")
    default String getAccountNumber(List<AccountDTO> accounts){
        return accounts.get(0).getAccountNumber();
    }
}
