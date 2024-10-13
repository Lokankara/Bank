package com.wallet.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long accountId;
    private Long customerId;
    private String accountType;
    private BigDecimal balance;
    private Timestamp createdAt;
}
