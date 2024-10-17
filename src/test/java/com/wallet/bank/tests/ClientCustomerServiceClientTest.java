package com.wallet.bank.tests;

import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.domain.SavingIAccount;
import com.wallet.bank.exceptions.ClientExistsException;
import com.wallet.bank.service.ClientBankService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientCustomerServiceClientTest {

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
        assertEquals(2, bank.getPrintedClients());
        assertEquals(2, bank.getEmailedClients());
        assertEquals(2, bank.getDebuggedClients());
    }

}
