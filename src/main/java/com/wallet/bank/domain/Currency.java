package com.wallet.bank.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class Currency implements Serializable {
	@Serial
	private static final long serialVersionUID = 64023495560719474L;
	private String code;
	private String country;

	public Currency (String code) {
       this.code = code;
    }

}
