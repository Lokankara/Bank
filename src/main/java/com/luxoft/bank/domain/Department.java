package com.luxoft.bank.domain;

import com.luxoft.bank.employees.BankEmployee;

public class Department {
	public String name;
	public BankEmployee manager;
	
	public Department(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BankEmployee getManager() {
		return manager;
	}
	
	public void setManager(BankEmployee manager) {
		this.manager = manager;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
