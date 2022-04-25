package com.luxoft.bank.account;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertNull;

public class AccountFactoryProviderTest {

    private AccountFactoryProvider factory;
    private AccountDetails saving;
    private AccountDetails checking;
    private AccountDetails current;

    @Before
    public void setUp() {
        factory = new AccountFactoryProvider();
        saving = AccountDetails.builder().id(1).amount(0.0).type("s").build();
        checking = AccountDetails.builder().id(2).amount(0.0).overdraft(0.1).type("c").build();
        current = AccountDetails.builder().id(2).amount(0.0).overdraft(0.1).type("current").build();
    }

    @Test
    public void testAccountSave() {
        Account account = factory.createAccount(saving);
        assertThat(account, instanceOf(Account.class));
    }

    @Test
    public void testCheckingAccount() {
        Account account = factory.createAccount(checking);
        assertThat(account, instanceOf(CheckingAccount.class));
    }

    @Test
    public void testValidCheckingAccount() {
        Account account = factory.createAccount(checking);
        assertThat(account, instanceOf(Account.class));
    }

    @Test
    public void testInvalidAccountType() {
        Account account = factory.createAccount(current);
        assertNull(account);
    }

}