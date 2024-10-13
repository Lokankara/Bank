package com.wallet.bank.tests;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.domain.SavingIAccount;
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
            new CheckingIAccount(1, 1000.0, -100.0);
        });
    }

    @Test
    public void testDepositNegativeValue() {
        SavingIAccount savingAccount = new SavingIAccount(1, 1000.0);
        assertThrows(IllegalArgumentException.class, () -> {
            savingAccount.deposit(-100.0);
        });
    }

    @Test
    public void testWithdrawNegativeValue() {
        CheckingIAccount checkingAccount = new CheckingIAccount(2, 1000.0, 100.0);
        assertThrows(IllegalArgumentException.class, () -> {
            checkingAccount.withdraw(-100.0);
        });
    }

    @Test
    public void testSavingAccountThrowException() {
        SavingIAccount savingAccount = new SavingIAccount(1, 1000.0);
        savingAccount.deposit(100.0);
        assertThrows(NotEnoughFundsException.class, () -> {
            savingAccount.withdraw(2000.0);
        });
    }

    @Test
    public void testCheckingAccountThrowException() {
        CheckingIAccount checkingAccount = new CheckingIAccount(2, 1000.0, 100.0);
        checkingAccount.deposit(100.0);
        assertThrows(OverdraftLimitExceededException.class, () -> {
            checkingAccount.withdraw(2000.0);
        });
    }

    @Test
    public void testClientExists() throws ClientExistsException {
        Bank bank = new Bank();
        Client client1 = new Client("Smith John", Gender.MALE);

        Set<IAccount> IAccounts = new HashSet<>();
        IAccounts.add(new SavingIAccount(1, 1000.0));
        IAccounts.add(new CheckingIAccount(2, 1000.0, 100.0));
        client1.setIAccounts(IAccounts);

        BankService.addClient(bank, client1);
        assertThrows(ClientExistsException.class, () -> {
            BankService.addClient(bank, client1);
        });
    }
}
