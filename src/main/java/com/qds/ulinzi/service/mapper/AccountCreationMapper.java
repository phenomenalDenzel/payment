package com.qds.ulinzi.service.mapper;

import com.qds.ulinzi.service.dto.AccountCreationDTO;
import com.qds.ulinzi.service.dto.CreateAccountRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AccountCreationMapper extends EntityMapper<AccountCreationDTO,CreateAccountRequest> {
    @Override
    CreateAccountRequest toEntity(AccountCreationDTO dto);

    @Override
    AccountCreationDTO toDto(CreateAccountRequest entity);

}
