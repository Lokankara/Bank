package com.wallet.bank.account;

import com.wallet.bank.exceptions.NotEnoughFundsException;
import com.wallet.bank.utils.Params;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class AbstractAccount implements Account, Cloneable, Serializable {

    @Serial
    private static final long serialVersionUID = -2272551373694344386L;
    private static final String SAVE = AccountType.SAVING.name();
    private static final String CHECK = AccountType.CHECKING.name();
    public static final int SAVING_ACCOUNT_TYPE = AccountType.SAVING.type;
    public static final int CHECKING_ACCOUNT_TYPE = AccountType.CHECKING.type;

    private final int id;
    @Setter
    private int type;
    private double balance;

    public AbstractAccount(int id, double amount) {
        this.id = id;
        this.balance = amount;
    }

    @Override
    public void deposit(final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Cannot deposit a negative amount");
        }
        this.balance += amount;
    }

    @Override
    public void withdraw(final double amount) throws NotEnoughFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative amount");
        }

        if (amount > maximumAmountToWithdraw()) {
            throw new NotEnoughFundsException(
                    id, balance, amount,
                    "Requested amount exceeds the maximum amount to withdraw");
        }
        this.balance -= amount;
    }

    public double maximumAmountToWithdraw() {
        if (type == SAVING_ACCOUNT_TYPE) {
            return balance;
        }
        if (type == CHECKING_ACCOUNT_TYPE) {
            CheckingAccount checkingAccount = (CheckingAccount) this;
            return checkingAccount.getBalance() + checkingAccount.getOverdraft();
        }
        return 0;
    }

    @Override
    public long decimalValue() {
        return Math.round(balance);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractAccount other = (AbstractAccount) obj;
        return id == other.id;
    }

    public static Account parse(Params params) {
        String accountType = params.get("accountType");
        if (accountType.equals(SAVE)) {
            return parse(params);
        }
        if (accountType.equals(CHECK)) {
            return parse(params);
        }
        return null;
    }

    @Override
    protected AbstractAccount clone() {
        try {
            return (AbstractAccount) super.clone();
        } catch (CloneNotSupportedException exception) {
            log.info("Cloning is not allowed");
            return this;
        }
    }
}
