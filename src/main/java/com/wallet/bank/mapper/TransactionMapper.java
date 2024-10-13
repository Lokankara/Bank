package com.wallet.bank.mapper;

import com.wallet.bank.domain.Transaction;
import com.wallet.bank.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .accountId(transaction.getAccount().getAccountId())
                .transactionDate(transaction.getTransactionDate())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .build();
    }
}
