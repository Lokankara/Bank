package com.wallet.bank.service;

import com.wallet.bank.account.Account;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class StreamBankReport implements BankReportStreams {
    private final ToDoubleFunction<? super Account> balance = Account::getBalance;
    private final Predicate<? super Account> allow = account -> account.getBalance() < 0;
    private final ToDoubleFunction<? super Account> decrease = account -> -account.getBalance();
    private final Function<? super Client, ? extends Collection<Account>> accounts = Client::getAccounts;

    @Override
    public int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    @Override
    public int getNumberOfAccounts(Bank bank) {
        return bank.getClients()
                .stream()
                .map(client -> client.getAccounts().size())
                .mapToInt(size -> size)
                .sum();
    }

    @Override
    public SortedSet<Client> getClientsSorted(Bank bank) {
        return bank.getClients()
                .stream()
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(Client::getName))));
    }

    @Override
    public double getTotalSumInAccounts(Bank bank) {

        return bank.getClients()
                .stream()
                .map(accounts)
                .flatMap(Collection::stream)
                .mapToDouble(balance)
                .sum();
    }

    @Override
    public SortedSet<Account> getAccountsSortedBySum(Bank bank) {

        TreeSet<Account> accountTreeSet = new TreeSet<>(Comparator.comparingDouble(balance));

        bank.getClients().stream().map(accounts).forEach(accountTreeSet::addAll);

        return accountTreeSet;
    }

    @Override
    public double getBankCreditSum(Bank bank) {
        return bank.getClients()
                .stream()
                .mapToDouble(client -> client.getAccounts()
                        .stream()
                        .filter(allow)
                        .mapToDouble(decrease)
                        .sum())
                .sum();
    }

    @Override
    public Map<Client, Collection<Account>> getCustomerAccounts(Bank bank) {

        return bank.getClients()
                .stream()
                .collect(Collectors.toMap(
                        client -> client,
                        accounts,
                        (a, b) -> b));
    }

    @Override
    public Map<String, List<Client>> getClientsByCity(Bank bank) {

        return bank.getClients()
                .stream()
                .collect(Collectors.groupingBy(
                        Client::getCity));
    }
}