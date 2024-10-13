package com.wallet.bank.tests;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.account.SavingIAccount;
import com.wallet.bank.bank.Bank;
import com.wallet.bank.bank.service.BankService;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.exceptions.ClientExistsException;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ClientExistsTest {

    @Test
    public void testBank() throws ClientExistsException {
        Bank bank = new Bank();
        Client client1 = new Client("Smith John", Gender.MALE);
        Set<IAccount> accounts1 = new HashSet<IAccount>();
        accounts1.add(new SavingIAccount(1, 1000.0));
        accounts1.add(new CheckingIAccount(2, 1000.0, 100.0));
        client1.setIAccounts(accounts1);

        Client client2 = new Client("Smith Michelle", Gender.FEMALE);
        Set<IAccount> accounts2 = new HashSet<IAccount>();
        accounts2.add(new SavingIAccount(3, 2000.0));
        accounts2.add(new CheckingIAccount(4, 1500.0, 200.0));
        client2.setIAccounts(accounts2);

        BankService.addClient(bank, client1);
        BankService.addClient(bank, client2);

        assertEquals(2, bank.getClients().size());
    }

}

