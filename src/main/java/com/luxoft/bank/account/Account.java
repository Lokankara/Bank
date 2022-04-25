package com.luxoft.bank.account;

import com.luxoft.bank.exceptions.NotEnoughFundsException;

public interface Account {
	void deposit(double amount);
	void withdraw(double amount) throws NotEnoughFundsException;
	int getId();
	double getBalance();
	long decimalValue();
	double maximumAmountToWithdraw();
}
