package com.wallet.bank.employees;

import com.wallet.bank.domain.Department;

public class BankEmployee {

	private String name;
	private Department department;
	private int salary;
	
	public BankEmployee() {
		
	}

	public BankEmployee(String name, Department department, int salary) { 
		this.name = name; 
		this.department = department; 
		this.salary = salary; 
	}
	
	public Department getDepartment() {
		return department;
	}

	public String getName() {
		return name;
	}
}
