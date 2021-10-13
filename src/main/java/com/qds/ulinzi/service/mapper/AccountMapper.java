package com.qds.ulinzi.service.mapper;

import com.qds.ulinzi.entity.Account;
import com.qds.ulinzi.service.dto.AccountDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "cdi",uses={MonnifyAccountMapper.class})
public interface AccountMapper extends EntityMapper<AccountDTO,Account> {

    @Override
    Account toEntity(AccountDTO dto);

    @Override
    AccountDTO toDto(Account entity);

    default Account fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Account account = new Account();
        account.setId(id);
        return account;
    }
}
