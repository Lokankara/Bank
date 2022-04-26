package com.luxoft.bank.account;

import static com.luxoft.bank.account.AccountType.CHECKING;
import static com.luxoft.bank.account.AccountType.SAVING;

public class AccountFactoryProvider {
        private static final String SAVE = SAVING.name();
        private static final String CHECK = CHECKING.name();

    static public Account createAccount(AccountDetails details) {
        int id = details.getId();
        double amount = details.getAmount();
        double overdraft = details.getOverdraft();
        String type = details.getType();

        if (type.equals(SAVE)) {
            return new SavingAccount(id, amount);
        } else if (type.equals(CHECK)) {
            return new CheckingAccount(id, amount, overdraft);
        } else {
            System.out.println("Invalid type");
        }
        return null;
    }
}
