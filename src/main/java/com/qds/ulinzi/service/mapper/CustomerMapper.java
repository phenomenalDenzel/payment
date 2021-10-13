package com.qds.ulinzi.service.mapper;

import com.qds.ulinzi.entity.Customer;
import com.qds.ulinzi.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "cdi",uses={AccountMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer>{
    @Override
    @Mapping(target = "createdDate",ignore = true)
    @Mapping(target = "lastModifiedDate",ignore = true)
    @Mapping(target = "externalAccounts",ignore = true)
    Customer toEntity(CustomerDTO dto);

    @Override
    @Mapping(target = "externalAccounts",ignore = true)
    CustomerDTO toDto(Customer entity);

    default Customer fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
