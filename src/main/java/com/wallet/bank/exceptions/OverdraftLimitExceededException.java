package com.wallet.bank.exceptions;

import java.io.Serial;

public class OverdraftLimitExceededException extends NotEnoughFundsException {
	
	@Serial
	private static final long serialVersionUID = -3737648528527468343L;
	private final double overdraft;

	public OverdraftLimitExceededException(NotEnoughFundsException e, double overdraft) {
		super(e.getMessage());
		this.id = e.id;
		this.balance = e.balance;
		this.amount = Math.round(amount * 100) / 100d;
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}
}
