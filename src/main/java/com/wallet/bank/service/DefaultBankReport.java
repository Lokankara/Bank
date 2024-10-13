package com.wallet.bank.service;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DefaultBankReport implements BankReport {
    @Override
    public int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    @Override
    public int getNumberOfAccounts(Bank bank) {
        return bank.getClients()
                .stream()
                .mapToInt(client -> getNumberOfClients(bank))
                .sum();
    }

    @Override
    public SortedSet<Client> getClientsSorted(Bank bank) {
        return new TreeSet<>(bank.getClients());
    }

    @Override
    public double getTotalSumInAccounts(Bank bank) {
        return bank.getClients()
                .stream()
                .mapToDouble(client -> client.getIAccounts()
                        .stream()
                        .mapToDouble(IAccount::getBalance)
                        .sum())
                .sum();
    }

    @Override
    public SortedSet<IAccount> getAccountsSortedBySum(Bank bank) {

        TreeSet<IAccount> sortedIAccounts =
                new TreeSet<>(Comparator
                        .comparingDouble(IAccount::getBalance));

        bank.getClients()
                .stream()
                .map(Client::getIAccounts)
                .forEach(sortedIAccounts::addAll);

        return sortedIAccounts;
    }

    @Override
    public double getBankCreditSum(Bank bank) {
        return bank.getClients()
                .stream()
                .mapToDouble(client -> client.getIAccounts()
                        .stream()
                        .filter(CheckingIAccount.class::isInstance)
                        .mapToDouble(IAccount::getBalance)
                        .sum())
                .sum();
    }

    @Override
    public Map<Client, Collection<IAccount>> getCustomerAccounts(Bank bank) {
        return bank.getClients()
                .stream()
                .collect(Collectors.toMap(client -> client, Client::getIAccounts, (a, b) -> b));
    }

    @Override
    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        Map<String, List<Client>> treeCity = new TreeMap<>();

        List<String> cities = bank.getClients()
                .stream()
                .map(Client::getCity)
                .toList();

        cities.forEach(city -> {
            List<Client> clients = bank.getClients()
                    .stream()
                    .filter(client -> Objects.equals(client.getCity(), city))
                    .collect(Collectors.toList());
            treeCity.put(city, clients);
        });
        return treeCity;
    }
}
