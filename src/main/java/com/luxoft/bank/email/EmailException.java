package com.luxoft.bank.email;

import java.io.Serial;

public class EmailException extends Exception {
	@Serial
    private static final long serialVersionUID = 3523913455438905283L;

	public EmailException(String message){
        super(message);
    }
}
