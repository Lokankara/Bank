package com.wallet.bank.annotations;

public abstract class ActiveRecord {

    public abstract void getById(int id);
    public abstract void getAll();
}
