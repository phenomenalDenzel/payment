package com.qds.ulinzi.service.strategy;

import com.qds.ulinzi.service.dto.AccountCreationDTO;

@FunctionalInterface
public interface AccountReferenceGenerator<T> {
    T generate(AccountCreationDTO accountCreationDTO);
}
