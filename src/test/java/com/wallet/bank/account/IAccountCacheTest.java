package com.wallet.bank.account;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class IAccountCacheTest {

    @Test
    public void cloneAccountCheckingNotNull() {
        AbstractIAccount checking = AccountCache.cloneAccount("CHECKING");
        assertNotNull(checking);
    }

    @Test
    public void cloneAccountSavingNotNull() {
        AbstractIAccount saving = AccountCache.cloneAccount("SAVING");
        assertNotNull(saving);
    }

    @Test
    public void cloneAccountCheckingInstance() {
        AbstractIAccount checking = AccountCache.cloneAccount("CHECKING");
        assertInstanceOf(IAccount.class, checking);
    }

    @Test
    public void cloneAccountSavingInstance() {
        AbstractIAccount saving = AccountCache.cloneAccount("SAVING");
        assertInstanceOf(IAccount.class, saving);
    }
}