package com.wallet.bank.service;

import com.wallet.bank.dao.CustomerRepository;
import com.wallet.bank.domain.Customer;
import com.wallet.bank.dto.CustomerDto;
import com.wallet.bank.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerMapper mapper;
    private final CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public CustomerDto createCustomer(Customer customer) {
        return mapper.toDto(customerRepository.save(customer));
    }
}
