package com.wallet.bank.tests;

import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.account.IAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.domain.SavingIAccount;
import com.wallet.bank.exceptions.ClientExistsException;
import com.wallet.bank.exceptions.NotEnoughFundsException;
import com.wallet.bank.service.BankReportStreams;
import com.wallet.bank.service.ClientBankService;
import com.wallet.bank.service.StreamBankReport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {
    @Test
    public void testBankReport() throws ClientExistsException, NotEnoughFundsException {
        Bank bank = new Bank();
        Client client1 = new Client("John", Gender.MALE, "New York");
        Client client2 = new Client("Michael", Gender.MALE, "London");
        Client client3 = new Client("Anna", Gender.FEMALE, "Berlin");

        IAccount IAccount1 = new SavingIAccount(1, 100);
        IAccount IAccount2 = new CheckingIAccount(2, 100, 20);
        client1.addAccount(IAccount1);
        client1.addAccount(IAccount2);

        ClientBankService.addClient(bank, client1);
        ClientBankService.addClient(bank, client2);
        ClientBankService.addClient(bank, client3);

        IAccount1.deposit(100);

        IAccount1.withdraw(10);
        IAccount2.withdraw(90);

        BankReportStreams bankReport = new StreamBankReport();

        assertEquals(3, bankReport.getNumberOfClients(bank));
        assertEquals(2, bankReport.getNumberOfAccounts(bank));

        HashSet<Client> treeSet = new HashSet<>();
        treeSet.add(client3);
        treeSet.add(client1);
        treeSet.add(client2);
        assertEquals(treeSet, bankReport.getClientsSorted(bank));

        assertEquals(200.0, bankReport.getTotalSumInAccounts(bank), 0.0001);

        HashSet<IAccount> accountsSortedBySum = new HashSet<>();
        accountsSortedBySum.add(IAccount2);
        accountsSortedBySum.add(IAccount1);
        assertEquals(accountsSortedBySum, bankReport.getAccountsSortedBySum(bank));

        Map<Client, Collection<IAccount>> map = new HashMap<>();
        map.put(client1, client1.getIAccounts());
        map.put(client2, client2.getIAccounts());
        map.put(client3, client3.getIAccounts());

        assertEquals(map, bankReport.getCustomerAccounts(bank));

        Map<String, List<Client>> mapClientByCity = new HashMap<>();
        ArrayList<Client> arrayList1 = new ArrayList<>();
        arrayList1.add(client1);
        mapClientByCity.put("New York", arrayList1);
        ArrayList<Client> arrayList2 = new ArrayList<>();
        arrayList2.add(client2);
        mapClientByCity.put("London", arrayList2);
        ArrayList<Client> arrayList3 = new ArrayList<>();
        arrayList3.add(client3);
        mapClientByCity.put("Berlin", arrayList3);

        assertEquals(mapClientByCity, bankReport.getClientsByCity(bank));
    }
}
