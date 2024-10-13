package com.wallet.bank.account;

import static com.wallet.bank.account.AccountType.CHECKING;
import static com.wallet.bank.account.AccountType.SAVING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IAccountFactoryProviderTest {
    private AccountDetails current;
    public static IAccount IAccountChecking;
    public static IAccount IAccountSaving;

    @BeforeEach
    public void setUp() {
        String save = SAVING.name();
        String check = CHECKING.name();
        AccountDetails saving = AccountDetails.builder().id(0).amount(0.0).type(save).build();
        AccountDetails checking = AccountDetails.builder().id(0).amount(0.0).overdraft(0.0).type(check).build();
        current = AccountDetails.builder().id(0).amount(0.0).overdraft(0.0).type("current").build();
        IAccountSaving = AccountFactoryProvider.createAccount(saving);
        IAccountChecking = AccountFactoryProvider.createAccount(checking);
    }

    @Test
    public void testAccountSave() {
        assertInstanceOf(IAccount.class, IAccountSaving);
    }

    @Test
    public void testValidCheckingAccount() {
        assertInstanceOf(CheckingIAccount.class, IAccountChecking);
    }

    @Test
    public void testInvalidAccountType() {
        IAccount IAccount = AccountFactoryProvider.createAccount(current);
        assertNull(IAccount);
    }

    @Test
    public void testValidCheckingAccountAndClone() {
        AbstractIAccount checking = AccountCache.cloneAccount("CHECKING");
        assertEquals(IAccountChecking, checking);
    }

    @Test
    public void testValidSavingAccountAndClone() {
        AbstractIAccount saving = AccountCache.cloneAccount("SAVING");
        assertEquals(IAccountSaving, saving);
    }

    @Test
    public void testSavingAccountAndShallowClone() {
        SavingIAccount save = new SavingIAccount(1, 1.0);
        AbstractIAccount clone = save.clone();
        assertEquals(clone, save);
    }

    @Test
    public void testSavingAccountAndDeepClone() {
        SavingIAccount savingAccount = new SavingIAccount(1, 1.0);
        AbstractIAccount clone = savingAccount.clone();
        Class<? extends SavingIAccount> clazz = savingAccount.getClass();
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
        CheckingIAccount checkingAccount = new CheckingIAccount(1, 1.0, 0.1);
        AbstractIAccount clone = checkingAccount.clone();
        Class<? extends CheckingIAccount> clazz = checkingAccount.getClass();
        int id = checkingAccount.getId();
        int type = checkingAccount.getType();
        double balance = checkingAccount.getBalance();

        assertEquals(clone.getClass(), clazz);
        assertEquals(clone.getId(), id);
        assertEquals(clone.getType(), type);
        assertEquals(clone.getBalance(), balance, .00001);
    }
}