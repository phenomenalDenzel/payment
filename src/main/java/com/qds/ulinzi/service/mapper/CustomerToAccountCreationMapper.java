package com.qds.ulinzi.service.mapper;

import com.qds.ulinzi.service.dto.AccountCreationDTO;
import com.qds.ulinzi.service.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CustomerToAccountCreationMapper extends EntityMapper<AccountCreationDTO, CustomerDTO> {

}
