package com.wallet.bank.tests;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.service.BankDataLoaderService;
import com.wallet.bank.service.BankReport;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankDataLoaderServiceTest {
    String clientsFile = "src/main/resources/clients.txt";
    Bank bank = new Bank();
    BankReport bankReport;

    @BeforeEach
    public void initialize() {
        BankDataLoaderService bankDataLoaderService = new BankDataLoaderService();
        bankDataLoaderService.readClients(bank, clientsFile);
        bankReport = new BankReport(bank);
    }

    @Test
    public void testNumberOfClients() {
        assertEquals(10, bankReport.getNumberOfClients());
    }

    @Test
    public void testClientsSorted() {
        SortedSet<Client> clients = bankReport.getClientsSorted();
        assertEquals(10, clients.size());
    }

    @Test
    public void testCustomerAccounts() {
        Map<Client, Collection<IAccount>> customerAccounts = bankReport.getCustomerAccounts();
        assertEquals(10, customerAccounts.size());
    }

    @Test
    public void testClientsByCity() {
        Map<String, ArrayList<Client>> clientsByCity = bankReport.getClientsByCity();
        assertEquals(2, clientsByCity.get("New York").size());
        assertEquals(8, clientsByCity.get("Boston").size());
    }
}
