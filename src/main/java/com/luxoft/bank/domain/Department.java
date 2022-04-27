package com.luxoft.bank.domain;

import com.luxoft.bank.employees.BankEmployee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {
	public String name;
	public BankEmployee manager;
	
	public Department(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
