package com.wallet.bank.mapper;

import com.wallet.bank.domain.Customer;
import com.wallet.bank.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerMapper {

    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .email(customer.getEmail())
                .createdAt(customer.getCreatedAt())
                .accounts(customer.getAccounts()
                        .stream()
                        .map(AccountMapper::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
