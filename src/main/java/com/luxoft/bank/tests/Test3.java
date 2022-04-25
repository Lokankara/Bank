package com.luxoft.bank.tests;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.luxoft.bank.account.Account;
import com.luxoft.bank.domain.Bank;
import com.luxoft.bank.account.CheckingAccount;
import com.luxoft.bank.domain.Client;
import com.luxoft.bank.domain.Gender;
import com.luxoft.bank.account.SavingAccount;
import com.luxoft.bank.exceptions.ClientExistsException;
import com.luxoft.bank.exceptions.NotEnoughFundsException;
import com.luxoft.bank.exceptions.OverdraftLimitExceededException;
import com.luxoft.bank.service.BankService;

public class Test3 {
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateCheckingAccount() {
		new CheckingAccount(1, 1000.0, -100.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegativeValue() {
		SavingAccount savingAccount = new SavingAccount(1, 1000.0);
		savingAccount.deposit(-100.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWithdrawNegativeValue() throws OverdraftLimitExceededException {
		CheckingAccount checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
		checkingAccount.withdraw(-100.0);
	}
	
	@Test(expected=NotEnoughFundsException.class)
	public void testSavingAccountThrowException() throws NotEnoughFundsException {
		SavingAccount savingAccount = new SavingAccount(1, 1000.0);
		savingAccount.deposit(100.0);
		savingAccount.withdraw(2000.0);
	}
	
	@Test(expected=OverdraftLimitExceededException.class)
	public void testCheckingAccountThrowException() throws OverdraftLimitExceededException {
		CheckingAccount checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
		checkingAccount.deposit(100.0);
		checkingAccount.withdraw(2000.0);
	}
	
	@Test(expected=ClientExistsException.class)
	public void testClientExists() throws ClientExistsException {
		Bank bank = new Bank();
		Client client1 = new Client("Smith John", Gender.MALE); 
		
		Set<Account> accounts = new HashSet<Account>();
		accounts.add(new SavingAccount(1, 1000.0));
		accounts.add(new CheckingAccount(2, 1000.0, 100.0));
		client1.setAccounts(accounts);
		
		BankService.addClient(bank, client1);
		BankService.addClient(bank, client1);
		
	}

}
