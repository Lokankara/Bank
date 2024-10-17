package com.wallet.bank.web;

import com.wallet.bank.domain.Customer;
import com.wallet.bank.dto.CustomerDto;
import com.wallet.bank.service.CustomerBankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends BaseController {

    public CustomerController(CustomerBankService customerBankService) {
        super(customerBankService);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        return new ResponseEntity<>(customerBankService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerBankService.addCustomer(customer), HttpStatus.CREATED);
    }
}
