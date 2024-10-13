package com.wallet.bank.bank.service;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.bank.Bank;
import com.wallet.bank.domain.Client;

import java.util.*;

public class BankReport {

    private final Bank bank;

    public BankReport(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    /* Returns the number of bank clients */
    public int getNumberOfClients() {
        return bank.getClients().size();
    }

    /* Returns the total number of accounts for all bank clients */
    public int getNumberOfAccounts() {
        Set<IAccount> IAccounts = new HashSet<>();
        for (Client client : bank.getClients())
            IAccounts.addAll(client.getIAccounts());
        return IAccounts.size();
    }

    /* Returns the set of clients in alphabetical order */
    public SortedSet<Client> getClientsSorted() {
        SortedSet<Client> clients = new TreeSet<>((client1, client2) -> {
            if (client1.getName() == null) {
                return 1;
            }
            return client1.getName().compareTo(client2.getName());
        });
        clients.addAll(bank.getClients());
        return clients;
    }

    /* Returns the total sum (balance) from the accounts of all bank clients */
    public double getTotalSumInAccounts() {
        double sum = 0.0;
        Set<IAccount> IAccounts = new HashSet<>();
        for (Client client : bank.getClients())
            IAccounts.addAll(client.getIAccounts());
        for (IAccount IAccount : IAccounts) {
            sum += IAccount.getBalance();
        }
        return Math.round(sum * 100) / 100d;
    }

    /*
     * Returns the set of all accounts. The list is ordered by current account
     * balance
     */
    public SortedSet<IAccount> getAccountsSortedBySum() {
        SortedSet<IAccount> result = new TreeSet<>((o1, o2) ->
                (int) Math.round(o1.getBalance() - o2.getBalance()));
        for (Client client : bank.getClients()) {
            result.addAll(client.getIAccounts());
        }
        return result;
    }

    /*
     * Returns the total amount of credits granted to the bank clients. That is,
     * the sum of all values above account balance for CheckingAccount
     */
    public double getBankCreditSum() {
        double result = 0.0;
        Set<IAccount> IAccounts = new HashSet<>();
        for (Client client : bank.getClients()) {
            IAccounts.addAll(client.getIAccounts());
        }
        for (IAccount IAccount : IAccounts)
            if (IAccount instanceof CheckingIAccount) {
                if ((IAccount).getBalance() < 0) {
                    result -= IAccount.getBalance();
                }
            }

        return Math.round(result * 100) / 100d;
    }

    /* Returns a map of client accounts */
    public Map<Client,Collection<IAccount>> getCustomerAccounts() {
        Map<Client, java.util.Collection<IAccount>> result = new HashMap<>();
        for (Client client : bank.getClients()) {
            result.put(client, client.getIAccounts());
        }
        return result;
    }

    /* Returns a map of cities and clients */
    public Map<String, ArrayList<Client>> getClientsByCity() {
        Set<Client> sortedClients = bank.getClients();
        Map<String, ArrayList<Client>> clientCities = new TreeMap<>();
        for (Client client : sortedClients) {
            String city = client.getCity();
            if (!clientCities.containsKey(city)) {
                ArrayList<Client> clientsNotYetInMap = new ArrayList<>();
                clientsNotYetInMap.add(client);
                clientCities.put(city, clientsNotYetInMap);
            } else {
                (clientCities.get(city)).add(client);
            }
        }
        return clientCities;
    }
}
