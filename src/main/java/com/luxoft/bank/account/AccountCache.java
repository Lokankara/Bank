package com.luxoft.bank.account;

import java.util.HashMap;
import java.util.Map;

import static com.luxoft.bank.account.AccountType.CHECKING;
import static com.luxoft.bank.account.AccountType.SAVING;

public class AccountCache {
    private static final String SAVE = SAVING.name();
    private static final String CHECK = CHECKING.name();

    private static final Map<String, AbstractAccount> cacheMap = new HashMap<>();

    static {
        AbstractAccount savingAccount = new SavingAccount(0, 0.0);
        AbstractAccount checkingAccount = new CheckingAccount(0, 0.0,0.0);
        cacheMap.put(SAVE, savingAccount);
        cacheMap.put(CHECK, checkingAccount);
    }

    public static AbstractAccount cloneAccount(String type) {
        final AbstractAccount abstractAccount = cacheMap.get(type);
        if (abstractAccount != null) {
            return abstractAccount.clone();
        }
        return null;
    }
}
