package com.wallet.bank.book.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "s_id")
    private Subscriber subscriber;

    @ManyToOne
    @JoinColumn(name = "b_id")
    private Book book;

    private LocalDate start;
    private LocalDate finish;
    private Boolean isActive;
}
