package com.luxoft.bank.exceptions;

import java.io.Serial;

public class BankException extends Exception {
	@Serial
	private static final long serialVersionUID = 3214520997410884213L;

	public BankException(String message){
	    super(message);
	}
}
