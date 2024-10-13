package com.wallet.bank.service;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public interface BankReportStreams {

    int getNumberOfClients(Bank bank);

    int getNumberOfAccounts(Bank bank);

    SortedSet<Client> getClientsSorted(Bank bank);

    double getTotalSumInAccounts(Bank bank);

    SortedSet<IAccount> getAccountsSortedBySum(Bank bank);

    double getBankCreditSum(Bank bank);

    Map<Client, Collection<IAccount>> getCustomerAccounts(Bank bank);

    Map<String, List<Client>> getClientsByCity(Bank bank);
}
