package com.wallet.bank.service;

import com.wallet.bank.account.Account;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public interface BankReport {

    int getNumberOfClients(Bank bank);

    int getNumberOfAccounts(Bank bank);

    SortedSet getClientsSorted(Bank bank);

    double getTotalSumInAccounts(Bank bank);

    SortedSet getAccountsSortedBySum(Bank bank);

    double getBankCreditSum(Bank bank);

    Map<Client, Collection<Account>> getCustomerAccounts(Bank bank);

    Map<String, List<Client>> getClientsByCity(Bank bank);

}
