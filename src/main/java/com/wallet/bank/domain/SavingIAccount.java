package com.wallet.bank.domain;

import com.wallet.bank.annotations.ActiveRecordEntity;
import com.wallet.bank.account.AbstractIAccount;

@ActiveRecordEntity(tableName = "SAVING_ACCOUNT", columnKeyName = "id")
public class SavingIAccount extends AbstractIAccount {
    public SavingIAccount(int id, double balance) {
        super(id, balance);
    }

    public double maximumAmountToWithdraw() {
        return getBalance();
    }

    @Override
    public String toString() {
        return String.format("Saving account %d, balance: %.2f", getId(), getBalance());
    }
}
