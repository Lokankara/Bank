package com.wallet.bank.tests;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.account.SavingIAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.service.ClientBankService;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.exceptions.ClientExistsException;
import com.wallet.bank.exceptions.NotEnoughFundsException;
import com.wallet.bank.exceptions.OverdraftLimitExceededException;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class NotEnoughFundsTest {

    public ClientBankService bankService;

    @Test
    public void testSavingAccount() throws NotEnoughFundsException {
        SavingIAccount savingAccount = new SavingIAccount(1, 1000.0);
        savingAccount.deposit(100.0);
        savingAccount.withdraw(50.0);
        assertEquals(1, savingAccount.getId());
        assertEquals(1050, savingAccount.getBalance(), 0);
        assertEquals(1050, savingAccount.maximumAmountToWithdraw(), 0);
    }

    @Test
    public void testCheckingAccount() throws OverdraftLimitExceededException {
        CheckingIAccount checkingAccount = new CheckingIAccount(2, 1000.0, 100.0);
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

        Set<IAccount> IAccounts = new HashSet<IAccount>();
        IAccount savingIAccount = new SavingIAccount(1, 1000.0);
        IAccounts.add(savingIAccount);
        IAccount checkingIAccount = new CheckingIAccount(2, 1000.0, 100.0);
        IAccounts.add(checkingIAccount);
        client.setIAccounts(IAccounts);

        assertEquals(2, client.getIAccounts().size());
        assertEquals("Mr. Smith John", client.getClientGreeting());
    }

    @Test
    public void testBank() throws ClientExistsException {
        Bank bank = new Bank();
        Client client1 = new Client("Smith John", Gender.MALE);
        bankService = new ClientBankService();
        Set<IAccount> accounts1 = new HashSet<>();
        IAccount savingIAccount1 = new SavingIAccount(1, 1000.0);
        accounts1.add(savingIAccount1);
        IAccount checkingIAccount1 = new CheckingIAccount(2, 1000.0, 100.0);
        accounts1.add(checkingIAccount1);
        client1.setIAccounts(accounts1);
        Client client2 = new Client("Smith Michelle", Gender.FEMALE);
        Set<IAccount> accounts2 = new HashSet<IAccount>();
        IAccount savingIAccount2 = new SavingIAccount(3, 2000.0);
        accounts2.add(savingIAccount2);
        IAccount checkingIAccount2 = new CheckingIAccount(4, 1500.0, 200.0);
        accounts2.add(checkingIAccount2);
        client2.setIAccounts(accounts2);
        bankService.addClient(bank, client1);
        bankService.addClient(bank, client2);
        assertEquals(2, bank.getClients().size());
    }
}
