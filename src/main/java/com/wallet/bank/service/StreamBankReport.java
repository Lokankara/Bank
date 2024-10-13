package com.wallet.bank.service;

import com.wallet.bank.account.IAccount;
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
    private final ToDoubleFunction<? super IAccount> balance = IAccount::getBalance;
    private final Predicate<? super IAccount> allow = account -> account.getBalance() < 0;
    private final ToDoubleFunction<? super IAccount> decrease = account -> -account.getBalance();
    private final Function<? super Client, ? extends Collection<IAccount>> accounts = Client::getIAccounts;

    @Override
    public int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    @Override
    public int getNumberOfAccounts(Bank bank) {
        return bank.getClients()
                .stream()
                .map(client -> client.getIAccounts().size())
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
    public SortedSet<IAccount> getAccountsSortedBySum(Bank bank) {

        TreeSet<IAccount> IAccountTreeSet = new TreeSet<>(Comparator.comparingDouble(balance));

        bank.getClients().stream().map(accounts).forEach(IAccountTreeSet::addAll);

        return IAccountTreeSet;
    }

    @Override
    public double getBankCreditSum(Bank bank) {
        return bank.getClients()
                .stream()
                .mapToDouble(client -> client.getIAccounts()
                        .stream()
                        .filter(allow)
                        .mapToDouble(decrease)
                        .sum())
                .sum();
    }

    @Override
    public Map<Client, Collection<IAccount>> getCustomerAccounts(Bank bank) {

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