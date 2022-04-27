package com.luxoft.bank.tests;

import java.util.HashSet;
import java.util.Set;

import com.luxoft.bank.account.Account;
import com.luxoft.bank.bank.Bank;
import com.luxoft.bank.account.CheckingAccount;
import com.luxoft.bank.domain.Client;
import com.luxoft.bank.domain.Gender;
import com.luxoft.bank.account.SavingAccount;
import com.luxoft.bank.exceptions.ClientExistsException;
import com.luxoft.bank.exceptions.NotEnoughFundsException;
import com.luxoft.bank.exceptions.OverdraftLimitExceededException;
import com.luxoft.bank.bank.service.BankService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test1 {
	
	@Test
	public void testSavingAccount() throws NotEnoughFundsException {
		SavingAccount savingAccount = new SavingAccount(1, 1000.0);
		savingAccount.deposit(100.0);
		savingAccount.withdraw(50.0);
		assertEquals(1, savingAccount.getId());
		assertEquals(1050, savingAccount.getBalance(), 0);
		assertEquals(1050, savingAccount.maximumAmountToWithdraw(), 0);
	}
	
	@Test
	public void testCheckingAccount() throws OverdraftLimitExceededException {
		CheckingAccount checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
		checkingAccount.deposit(100.0);
		checkingAccount.withdraw(1150.0);
		assertEquals(2, checkingAccount.getId());
		assertEquals(-50, checkingAccount.getBalance(), 0);
		assertEquals(100, checkingAccount.getOverdraft(), 0);
		assertEquals(50, checkingAccount.maximumAmountToWithdraw(), 0);
	}
	
	@Test
	public void testClient() {
		Client client = new Client("Smith John", Gender.MALE); 
		
		Set<Account> accounts = new HashSet<Account>();
		Account savingAccount = new SavingAccount(1, 1000.0);
		accounts.add(savingAccount);
		Account checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
		accounts.add(checkingAccount);
		client.setAccounts(accounts);
		
		assertEquals(2, client.getAccounts().size());
		assertEquals("Mr. Smith John", client.getClientGreeting());
	}
	
	@Test
	public void testBank() throws ClientExistsException {
		Bank bank = new Bank();
		Client client1 = new Client("Smith John", Gender.MALE); 
		
		Set<Account> accounts1 = new HashSet<Account>();
		Account savingAccount1 = new SavingAccount(1, 1000.0);
		accounts1.add(savingAccount1);
		Account checkingAccount1 = new CheckingAccount(2, 1000.0, 100.0);
		accounts1.add(checkingAccount1);
		client1.setAccounts(accounts1);
		
		Client client2 = new Client("Smith Michelle", Gender.FEMALE); 
		
		Set<Account> accounts2 = new HashSet<Account>();
		Account savingAccount2 = new SavingAccount(3, 2000.0);
		accounts2.add(savingAccount2);
		Account checkingAccount2 = new CheckingAccount(4, 1500.0, 200.0);
		accounts2.add(checkingAccount2);
		
		client2.setAccounts(accounts2);
		
		BankService.addClient(bank, client1);
		BankService.addClient(bank, client2);
		
		assertEquals(2, bank.getClients().size());
	}

}
