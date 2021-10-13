package com.qds.ulinzi.service.mapper;

import com.qds.ulinzi.entity.ExternalAccount;
import com.qds.ulinzi.service.dto.CustomerAccountDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CustomerAccountDetailsMapper extends EntityMapper<CustomerAccountDetails, ExternalAccount> {
    @Override
    CustomerAccountDetails toDto(ExternalAccount entity);
}
