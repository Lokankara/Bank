package com.wallet.bank.account;

import lombok.extern.slf4j.Slf4j;

import static com.wallet.bank.account.AccountType.CHECKING;
import static com.wallet.bank.account.AccountType.SAVING;

@Slf4j
public class AccountFactoryProvider {
    private static final String SAVE = SAVING.name();
    private static final String CHECK = CHECKING.name();

    protected static IAccount createAccount(AccountDetails details) {
        int id = details.getId();
        double amount = details.getAmount();
        double overdraft = details.getOverdraft();
        String type = details.getType();

        if (type.equals(SAVE)) {
            return new SavingIAccount(id, amount);
        } else if (type.equals(CHECK)) {
            return new CheckingIAccount(id, amount, overdraft);
        } else {
            log.info("Invalid type");
        }
        return null;
    }
}
