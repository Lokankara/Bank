package com.luxoft.bank.account;

import org.junit.Before;
import org.junit.Test;

import static com.luxoft.bank.account.AccountType.CHECKING;
import static com.luxoft.bank.account.AccountType.SAVING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AccountFactoryProviderTest {
    private AccountDetails current;
    public static Account accountChecking;
    public static Account accountSaving;

    @Before
    public void setUp() {
        String save = SAVING.name();
        String check = CHECKING.name();
        AccountDetails saving = AccountDetails.builder().id(0).amount(0.0).type(save).build();
        AccountDetails checking = AccountDetails.builder().id(0).amount(0.0).overdraft(0.0).type(check).build();
        current = AccountDetails.builder().id(0).amount(0.0).overdraft(0.0).type("current").build();
        accountSaving = AccountFactoryProvider.createAccount(saving);
        accountChecking = AccountFactoryProvider.createAccount(checking);
    }

    @Test
    public void testAccountSave() {
        assertThat(accountSaving, instanceOf(Account.class));
    }

    @Test
    public void testValidCheckingAccount() {
        assertThat(accountChecking, instanceOf(CheckingAccount.class));
    }

    @Test
    public void testInvalidAccountType() {
        Account account = AccountFactoryProvider.createAccount(current);
        assertNull(account);
    }

    @Test
    public void testValidCheckingAccountAndClone() {
        AbstractAccount checking = AccountCache.cloneAccount("CHECKING");
        assertEquals(accountChecking, checking);
    }

    @Test
    public void testValidSavingAccountAndClone() {
        AbstractAccount saving = AccountCache.cloneAccount("SAVING");
        assertEquals(accountSaving, saving);
    }

    @Test
    public void testSavingAccountAndShallowClone() {
        SavingAccount save = new SavingAccount(1, 1.0);
        AbstractAccount clone = save.clone();
        assertEquals(clone, save);
    }

    @Test
    public void testSavingAccountAndDeepClone() {
        SavingAccount savingAccount = new SavingAccount(1, 1.0);
        AbstractAccount clone = savingAccount.clone();
        Class<? extends SavingAccount> clazz = savingAccount.getClass();
        int id = savingAccount.getId();
        int type = savingAccount.getType();
        double balance = savingAccount.getBalance();

        assertEquals(clone.getClass(), clazz);
        assertEquals(clone.getId(), id);
        assertEquals(clone.getType(), type);
        assertEquals(clone.getBalance(), balance, .00001);
    }

    @Test
    public void testCheckingAccountAndDeepClone() {
        CheckingAccount checkingAccount = new CheckingAccount(1, 1.0, 0.1);
        AbstractAccount clone = checkingAccount.clone();
        Class<? extends CheckingAccount> clazz = checkingAccount.getClass();
        int id = checkingAccount.getId();
        int type = checkingAccount.getType();
        double balance = checkingAccount.getBalance();

        assertEquals(clone.getClass(), clazz);
        assertEquals(clone.getId(), id);
        assertEquals(clone.getType(), type);
        assertEquals(clone.getBalance(), balance, .00001);
    }
}