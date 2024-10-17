package com.wallet.bank.service;

import com.wallet.bank.domain.Customer;
import com.wallet.bank.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class CustomerService {

    public abstract List<CustomerDto> getAllCustomers();

    public abstract CustomerDto addCustomer(Customer customer);

    public abstract CustomerDto getById(Long id);
}
