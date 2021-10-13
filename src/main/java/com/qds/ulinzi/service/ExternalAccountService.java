package com.qds.ulinzi.service;

import com.qds.ulinzi.entity.ExternalAccount;
import com.qds.ulinzi.service.dto.AccountCreationDTO;

import java.util.Optional;

public interface ExternalAccountService {
  Optional<ExternalAccount> createAccount(AccountCreationDTO accountCreationDTO);

}
