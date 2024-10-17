package com.wallet.bank.web;

import com.wallet.bank.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseController {
    protected final CustomerService customerBankService;
}
