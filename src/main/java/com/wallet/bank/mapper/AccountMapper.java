package com.wallet.bank.mapper;

import com.wallet.bank.domain.Account;
import com.wallet.bank.dto.AccountDto;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public static AccountDto toDto(Account account) {
        return AccountDto.builder()
                .accountId(account.getAccountId())
                .customerId(account.getCustomer().getCustomerId())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
