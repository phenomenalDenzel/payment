package com.qds.ulinzi.service;

import com.qds.ulinzi.entity.Customer;
import com.qds.ulinzi.repository.CustomerRepository;
import com.qds.ulinzi.service.dto.CustomerDTO;
import com.qds.ulinzi.service.mapper.CustomerMapper;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {
    @Inject
    CustomerMapper customerMapper;

    @Inject
    CustomerRepository customerRepository;

    @Transactional
    public Customer save(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        if (StringUtils.isBlank(customer.getCustomerCode()))
            customer.generateCustomerCode();
        customerRepository.persist(customer);
        return customer;
    }

    public Optional<Customer> retrieveCustomer(String customerEmail) {
        return customerRepository.findByCustomerEmail(customerEmail);
    }

    public Optional<CustomerDTO> getCustomer(String customerEmail) {
        return customerRepository.findByCustomerEmail(customerEmail)
                .map(customerMapper::toDto);
    }

    public Optional<CustomerDTO> retrieveCustomerByEmailAndCode(String customerEmail, String customerCode){
        return customerRepository.findByCustomerEmailAndCustomerCode(customerEmail,customerCode)
                .map(customerMapper::toDto);
    }
}
