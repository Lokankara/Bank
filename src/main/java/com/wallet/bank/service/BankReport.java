package com.wallet.bank.service;

import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.account.IAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public record BankReport(Bank bank) {

    /**
     * Returns the number of bank clients
     */
    public int getNumberOfClients() {
        return bank.getClients().size();
    }

    /**
     * Returns the total number of accounts for all bank clients
     */
    public int getNumberOfAccounts() {
        return bank.getClients()
                .stream()
                .flatMap(client -> client.getIAccounts()
                        .stream())
                .collect(Collectors.toSet())
                .size();
    }

    /**
     * Returns the set of clients in alphabetical order
     */
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

    /**
     * Returns the total sum (balance) from the accounts of all bank clients
     */
    public double getTotalSumInAccounts() {
        double sum = bank.getClients()
                .stream()
                .flatMap(client -> client.getIAccounts()
                        .stream())
                .collect(Collectors.toSet())
                .stream()
                .mapToDouble(IAccount::getBalance)
                .sum();
        return Math.round(sum * 100) / 100d;
    }

    /**
     * Returns the set of all accounts. The list is ordered by current account
     * balance
     */
    public SortedSet<IAccount> getAccountsSortedBySum() {
        SortedSet<IAccount> result = new TreeSet<>((o1, o2) ->
                (int) Math.round(o1.getBalance() - o2.getBalance()));
        bank.getClients().stream().map(Client::getIAccounts).forEach(result::addAll);
        return result;
    }

    /**
     * Returns the total amount of credits granted to the bank clients. That is,
     * the sum of all values above account balance for CheckingAccount
     */
    public double getBankCreditSum() {
        double result = 0.0;
        for (IAccount IAccount : bank.getClients()
                .stream().flatMap(client -> client.getIAccounts()
                        .stream())
                .collect(Collectors.toSet()))
            if (IAccount instanceof CheckingIAccount) {
                if ((IAccount).getBalance() < 0) {
                    result -= IAccount.getBalance();
                }
            }

        return Math.round(result * 100) / 100d;
    }

    /**
     * Returns a map of client accounts
     */
    public Map<Client, Collection<IAccount>> getCustomerAccounts() {
        return bank.getClients()
                .stream()
                .collect(Collectors.toMap(client -> client, Client::getIAccounts, (a, b) -> b));

    }

    /**
     * Returns a map of cities and clients
     */
    public Map<String, ArrayList<Client>> getClientsByCity() {
        Map<String, ArrayList<Client>> clientCities = new TreeMap<>();
        for (Client client : bank.getClients()) {
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
