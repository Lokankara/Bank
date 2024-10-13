package com.wallet.bank.service;

import com.wallet.bank.dao.TransactionRepository;
import com.wallet.bank.domain.Transaction;
import com.wallet.bank.dto.TransactionDto;
import com.wallet.bank.mapper.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper mapper;

    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(mapper::toDto).toList();
    }

    public TransactionDto createTransaction(Transaction transaction) {
        return mapper.toDto(transactionRepository.save(transaction));
    }
}
