package com.wallet.bank.tests;

import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.domain.SavingIAccount;
import com.wallet.bank.exceptions.ClientExistsException;
import com.wallet.bank.exceptions.NotEnoughFundsException;
import com.wallet.bank.exceptions.OverdraftLimitExceededException;
import com.wallet.bank.service.ClientBankService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingIAccountTest {

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
        client.addAccount(new SavingIAccount(1, 1000.0));
        client.addAccount(new CheckingIAccount(2, 1000.0, 100.0));
        assertEquals(2, client.getIAccounts().size());
        assertEquals("Mr. Smith John", client.getClientGreeting());
    }

    @Test
    public void testBank() throws ClientExistsException {
        Bank bank = new Bank();
        Client client1 = new Client("Smith John", Gender.MALE);
        client1.addAccount(new SavingIAccount(1, 1000.0));
        client1.addAccount(new CheckingIAccount(2, 1000.0, 100.0));

        Client client2 = new Client("Smith Michelle", Gender.FEMALE);
        client2.addAccount(new SavingIAccount(3, 2000.0));
        client2.addAccount(new CheckingIAccount(4, 1500.0, 200.0));

        ClientBankService.addClient(bank, client1);
        ClientBankService.addClient(bank, client2);

        assertEquals(2, bank.getClients().size());
        assertEquals("Mr. Smith John", bank.getClients().get(0).getClientGreeting());
        assertEquals("Ms. Smith Michelle", bank.getClients().get(1).getClientGreeting());
    }
}
