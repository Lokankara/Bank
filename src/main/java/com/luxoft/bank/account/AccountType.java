package com.luxoft.bank.account;

public enum AccountType {

    SAVING(1),
    CHECKING(2);

    public final int type;

    private AccountType(int type) {
        this.type = type;
    }
}
