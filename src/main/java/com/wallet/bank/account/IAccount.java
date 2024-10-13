package com.wallet.bank.account;

import com.wallet.bank.exceptions.NotEnoughFundsException;

public interface IAccount {

    void deposit(double amount);

    void withdraw(double amount) throws NotEnoughFundsException;

    int getId();

    double getBalance();

    long decimalValue();

    double maximumAmountToWithdraw();
}
