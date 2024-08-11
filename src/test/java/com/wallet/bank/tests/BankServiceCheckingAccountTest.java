package com.wallet.bank.tests;

import com.wallet.bank.account.Account;
import com.wallet.bank.account.CheckingAccount;
import com.wallet.bank.account.SavingAccount;
import com.wallet.bank.bank.Bank;
import com.wallet.bank.bank.service.BankService;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.exceptions.ClientExistsException;
import com.wallet.bank.exceptions.NotEnoughFundsException;
import com.wallet.bank.exceptions.OverdraftLimitExceededException;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BankServiceCheckingAccountTest {

    @Test
    public void testCreateCheckingAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CheckingAccount(1, 1000.0, -100.0);
        });
    }

    @Test
    public void testDepositNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount savingAccount = new SavingAccount(1, 1000.0);
            savingAccount.deposit(-100.0);
        });
    }

    @Test
    public void testWithdrawNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            CheckingAccount checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
            checkingAccount.withdraw(-100.0);
        });
    }

    @Test
    public void testSavingAccountThrowException() {
        assertThrows(NotEnoughFundsException.class, () -> {
            SavingAccount savingAccount = new SavingAccount(1, 1000.0);
            savingAccount.deposit(100.0);
            savingAccount.withdraw(2000.0);
        });
    }

    @Test
    public void testCheckingAccountThrowException() {
        assertThrows(OverdraftLimitExceededException.class, () -> {
            CheckingAccount checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
            checkingAccount.deposit(100.0);
            checkingAccount.withdraw(2000.0);
        });
    }

    @Test
    public void testClientExists() {
        assertThrows(ClientExistsException.class, () -> {
            Bank bank = new Bank();
            Client client1 = new Client("Smith John", Gender.MALE);

            Set<Account> accounts = new HashSet<>();
            accounts.add(new SavingAccount(1, 1000.0));
            accounts.add(new CheckingAccount(2, 1000.0, 100.0));
            client1.setAccounts(accounts);

            BankService.addClient(bank, client1);
            BankService.addClient(bank, client1);
        });
    }
}
