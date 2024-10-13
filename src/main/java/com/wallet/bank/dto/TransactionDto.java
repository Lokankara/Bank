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
public class TransactionDto {

    private Long accountId;
    private Long transactionId;
    private BigDecimal amount;
    private String transactionType;
    private Timestamp transactionDate;

}
