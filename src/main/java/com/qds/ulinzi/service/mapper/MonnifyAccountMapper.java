package com.qds.ulinzi.service.mapper;

import com.qds.ulinzi.entity.MonnifyAccount;
import com.qds.ulinzi.service.dto.MonnifyAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "cdi",uses={CustomerMapper.class})
public interface MonnifyAccountMapper extends EntityMapper<MonnifyAccountDTO, MonnifyAccount>{

    @Override
    @Mapping(source="customer.id", target="customerId")
    public MonnifyAccountDTO toDto(MonnifyAccount entity);

    @Override
    @Mapping(target="lastModifiedDate", ignore=true)
    @Mapping(target="accounts", ignore=true)
    @Mapping(source="customerId", target="customer")
    public MonnifyAccount toEntity(MonnifyAccountDTO dto);


    default MonnifyAccount fromId(UUID id) {
        if (id == null) {
            return null;
        }
        MonnifyAccount account = new MonnifyAccount();
        account.setId(id);
        return account;
    }
}
