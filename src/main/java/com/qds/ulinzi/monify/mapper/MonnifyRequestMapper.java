package com.qds.ulinzi.monify.mapper;

import com.qds.ulinzi.monify.dto.AccountCreationRequest;
import com.qds.ulinzi.service.dto.AccountCreationDTO;
import com.qds.ulinzi.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface MonnifyRequestMapper extends EntityMapper<AccountCreationDTO, AccountCreationRequest> {
}

