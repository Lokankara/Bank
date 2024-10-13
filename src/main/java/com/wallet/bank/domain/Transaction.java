package com.wallet.bank.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@ToString
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    public Transaction() {
        this.transactionDate = new Timestamp(System.currentTimeMillis());
    }
}
