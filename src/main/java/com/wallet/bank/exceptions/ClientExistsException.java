package com.wallet.bank.exceptions;

import java.io.Serial;

public class ClientExistsException extends BankException {
	@Serial
	private static final long serialVersionUID = -8368249553360028667L;

	public ClientExistsException(String message) {
		super(message);
	}

}
