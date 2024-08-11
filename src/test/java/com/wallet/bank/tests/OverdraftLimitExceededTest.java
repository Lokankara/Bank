package com.wallet.bank.tests;

import com.wallet.bank.account.Account;
import com.wallet.bank.account.CheckingAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.domain.SavingAccount;
import com.wallet.bank.exceptions.ClientExistsException;
import com.wallet.bank.exceptions.NotEnoughFundsException;
import com.wallet.bank.exceptions.OverdraftLimitExceededException;
import com.wallet.bank.service.BankService;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OverdraftLimitExceededTest {

    @Test
    public void testCreateCheckingAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CheckingAccount(1, 1000.0, -100.0);
        });
    }

    @Test
    public void testDepositNegativeValue() {
        SavingAccount savingAccount = new SavingAccount(1, 1000.0);
        assertThrows(IllegalArgumentException.class, () -> {
            savingAccount.deposit(-100.0);
        });
    }

    @Test
    public void testWithdrawNegativeValue() {
        CheckingAccount checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            checkingAccount.withdraw(-100.0);
        });
    }

    @Test
    public void testSavingAccountThrowException() {
        SavingAccount savingAccount = new SavingAccount(1, 1000.0);
        savingAccount.deposit(100.0);
        assertThrows(NotEnoughFundsException.class, () -> {
            savingAccount.withdraw(2000.0);
        });
    }

    @Test
    public void testCheckingAccountThrowException() {
        CheckingAccount checkingAccount = new CheckingAccount(2, 1000.0, 100.0);
        checkingAccount.deposit(100.0);
        assertThrows(OverdraftLimitExceededException.class, () -> {
            checkingAccount.withdraw(2000.0);
        });
    }

    @Test
    public void testClientExists() throws ClientExistsException {
        Bank bank = new Bank();
        Client client1 = new Client("Smith John", Gender.MALE);

        Set<Account> accounts = new HashSet<>();
        accounts.add(new SavingAccount(1, 1000.0));
        accounts.add(new CheckingAccount(2, 1000.0, 100.0));
        client1.setAccounts(accounts);

        BankService.addClient(bank, client1);
        assertThrows(ClientExistsException.class, () -> {
            BankService.addClient(bank, client1);
        });
    }
}
