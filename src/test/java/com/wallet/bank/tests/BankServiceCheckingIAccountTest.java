package com.wallet.bank.tests;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.account.SavingIAccount;
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

public class BankServiceCheckingIAccountTest {

    @Test
    public void testCreateCheckingAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CheckingIAccount(1, 1000.0, -100.0);
        });
    }

    @Test
    public void testDepositNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            SavingIAccount savingAccount = new SavingIAccount(1, 1000.0);
            savingAccount.deposit(-100.0);
        });
    }

    @Test
    public void testWithdrawNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            CheckingIAccount checkingAccount = new CheckingIAccount(2, 1000.0, 100.0);
            checkingAccount.withdraw(-100.0);
        });
    }

    @Test
    public void testSavingAccountThrowException() {
        assertThrows(NotEnoughFundsException.class, () -> {
            SavingIAccount savingAccount = new SavingIAccount(1, 1000.0);
            savingAccount.deposit(100.0);
            savingAccount.withdraw(2000.0);
        });
    }

    @Test
    public void testCheckingAccountThrowException() {
        assertThrows(OverdraftLimitExceededException.class, () -> {
            CheckingIAccount checkingAccount = new CheckingIAccount(2, 1000.0, 100.0);
            checkingAccount.deposit(100.0);
            checkingAccount.withdraw(2000.0);
        });
    }

    @Test
    public void testClientExists() {
        assertThrows(ClientExistsException.class, () -> {
            Bank bank = new Bank();
            Client client1 = new Client("Smith John", Gender.MALE);

            Set<IAccount> IAccounts = new HashSet<>();
            IAccounts.add(new SavingIAccount(1, 1000.0));
            IAccounts.add(new CheckingIAccount(2, 1000.0, 100.0));
            client1.setIAccounts(IAccounts);

            BankService.addClient(bank, client1);
            BankService.addClient(bank, client1);
        });
    }
}
