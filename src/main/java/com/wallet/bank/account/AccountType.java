package com.wallet.bank.account;

public enum AccountType {

    SAVING(1),
    CHECKING(2);

    final int type;

    AccountType(int type) {
        this.type = type;
    }
}
