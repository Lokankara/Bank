package com.wallet.bank.account;

import java.util.HashMap;
import java.util.Map;

import static com.wallet.bank.account.AccountType.CHECKING;
import static com.wallet.bank.account.AccountType.SAVING;

public class AccountCache {
    private static final String SAVE = SAVING.name();
    private static final String CHECK = CHECKING.name();

    private static final Map<String, AbstractIAccount> cacheMap = new HashMap<>();

    static {
        AbstractIAccount savingAccount = new SavingIAccount(0, 0.0);
        AbstractIAccount checkingAccount = new CheckingIAccount(0, 0.0,0.0);
        cacheMap.put(SAVE, savingAccount);
        cacheMap.put(CHECK, checkingAccount);
    }

    public static AbstractIAccount cloneAccount(String type) {
        final AbstractIAccount abstractAccount = cacheMap.get(type);
        if (abstractAccount != null) {
            return abstractAccount.clone();
        }
        return null;
    }
}
