package com.wallet.bank.account;

import com.wallet.bank.domain.Currency;
import com.wallet.bank.exceptions.NotEnoughFundsException;
import com.wallet.bank.exceptions.OverdraftLimitExceededException;
import com.wallet.bank.utils.Params;

import java.io.Serial;
import java.util.Formatter;
import java.util.Locale;

public class CheckingIAccount extends AbstractIAccount {

    @Serial
    private static final long serialVersionUID = 7922392307762434334L;
    private final double overdraft;
    private Currency currency;

    public CheckingIAccount(int id, double amount, double overdraft) {
        super(id, amount);
        if (overdraft < 0) {
            throw new IllegalArgumentException(
                    "Cannot create an account with a starting negative overdraft");
        }
        this.overdraft = overdraft;
        this.setType(CHECKING_ACCOUNT_TYPE);
    }

    public CheckingIAccount(int id, double amount, double overdraft, Currency currency) {
        super(id, amount);
        if (overdraft < 0) {
            throw new IllegalArgumentException(
                    "Cannot create an account with a starting negative overdraft");
        }
        this.overdraft = overdraft;
        this.currency = currency;
        this.setType(CHECKING_ACCOUNT_TYPE);
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void withdraw(double value) throws OverdraftLimitExceededException {
        try {
            super.withdraw(value);
        } catch (NotEnoughFundsException notEnoughFundsException) {
            throw new OverdraftLimitExceededException(notEnoughFundsException, overdraft);
        }
    }

    @Override
    public String toString() {
        Formatter fmt = new Formatter(Locale.US);
        String stringAccount = fmt.format(
                "Checking account %d, balance: %.2f, overdraft: %.2f",
                getId(), getBalance(), overdraft).toString();
        fmt.close();
        return stringAccount;
    }

    public static IAccount parse(Params params) {
        String id = params.get("id");
        String balance = params.get("balance");
        String overdraft = params.get("overdraft");
        String currency = params.get("currency");
        return new CheckingIAccount(
                Integer.parseInt(id),
                Double.parseDouble(balance),
                Double.parseDouble(overdraft),
                new Currency(currency));
    }

    public double getOverdraft() {
        return overdraft;
    }
}
