package com.luxoft.bank.account;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

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
        assertThat(checking, instanceOf(Account.class));
    }

    @Test
    public void cloneAccountSavingInstance() {
        AbstractAccount saving = AccountCache.cloneAccount("SAVING");
        assertThat(saving, instanceOf(Account.class));
    }

}