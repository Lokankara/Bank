package com.luxoft.bank.employees;

import com.luxoft.bank.domain.Department;

public class BankEmployee {

	public String name; 
	public Department department; 
	public int salary; 
	
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
	
}
