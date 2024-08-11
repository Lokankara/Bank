package com.wallet.bank.account;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class AccountCacheTest {

    @Test
    public void cloneAccountCheckingNotNull() {
        AbstractAccount checking = AccountCache.cloneAccount("CHECKING");
        assertNotNull(checking);
    }

    @Test
    public void cloneAccountSavingNotNull() {
        AbstractAccount saving = AccountCache.cloneAccount("SAVING");
        assertNotNull(saving);
    }

    @Test
    public void cloneAccountCheckingInstance() {
        AbstractAccount checking = AccountCache.cloneAccount("CHECKING");
        assertInstanceOf(Account.class, checking);
    }

    @Test
    public void cloneAccountSavingInstance() {
        AbstractAccount saving = AccountCache.cloneAccount("SAVING");
        assertInstanceOf(Account.class, saving);
    }
}