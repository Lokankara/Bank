package com.wallet.bank.tests;

import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.service.BankDataLoaderService;
import com.wallet.bank.service.ClientBankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        BankDataLoaderService bankDataLoaderService = new BankDataLoaderService(new ClientBankService());
        bankDataLoaderService.readClients(bank, clientsFile);
        assertEquals(10, bank.getClients().size());
        assertTrue(bank.getClients().containsAll(clients));
        bank.getClients().forEach(client -> assertEquals(1, client.getIAccounts().size()));
    }
}
