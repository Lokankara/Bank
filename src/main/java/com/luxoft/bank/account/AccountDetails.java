package com.luxoft.bank.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDetails {
    private int id;
    private double amount;
    private double overdraft;
    private String type;

}
