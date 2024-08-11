package com.wallet.bank.tests;

import com.wallet.bank.bank.Bank;
import com.wallet.bank.bank.service.BankDataLoaderService;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankDataLoaderServiceClientTest {
    Set<Client> clients = new HashSet<>();
    String clientsFile = "src/main/resources/clients.txt";
    String testSerialFile = "src/main/resources/bank.ser";

    @BeforeEach
    public void initializeClients() {
        clients.add(new Client(("John"), Gender.MALE));
        clients.add(new Client(("Michelle"), Gender.FEMALE));
        clients.add(new Client(("James"), Gender.MALE));
        clients.add(new Client(("Brian"), Gender.MALE));
        clients.add(new Client(("Daniel"), Gender.MALE));
        clients.add(new Client(("Katherine"), Gender.FEMALE));
        clients.add(new Client(("Eugene"), Gender.MALE));
        clients.add(new Client(("Gabrielle"), Gender.FEMALE));
        clients.add(new Client(("Frank"), Gender.MALE));
        clients.add(new Client(("George"), Gender.MALE));
    }

    @Test
    public void testLoadFeed() {
        Bank bank = new Bank();
        BankDataLoaderService bankDataLoaderService = new BankDataLoaderService(bank);
        bankDataLoaderService.readClients(clientsFile);
        assertEquals(10, bank.getClients().size());
        assertTrue(bank.getClients().containsAll(clients));

        for (Client client : bank.getClients()) {
            assertEquals(1, client.getAccounts().size());
        }
    }
}
