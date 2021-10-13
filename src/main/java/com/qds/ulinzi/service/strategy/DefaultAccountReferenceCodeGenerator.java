package com.qds.ulinzi.service.strategy;

import com.qds.ulinzi.service.dto.AccountCreationDTO;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.Random;

@ApplicationScoped
public class DefaultAccountReferenceCodeGenerator implements AccountReferenceGenerator<String>{

    @Override
    public String generate(AccountCreationDTO accountCreationDTO) {
        Random random = new Random();
        return "UL-" + Instant.now().toEpochMilli()+"-"+ String.format("%04d", random.nextInt(10000));
    }
}
