package com.wallet.bank.tests;

import com.wallet.bank.account.CheckingIAccount;
import com.wallet.bank.domain.SavingIAccount;
import com.wallet.bank.exceptions.NotEnoughFundsException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingSavingIAccountTest {
    SavingIAccount savingAccount1;
    SavingIAccount savingAccount2;
    CheckingIAccount checkingAccount1;
    CheckingIAccount checkingAccount2;

    @BeforeEach
    public void initializeAccounts() {
        savingAccount1 = new SavingIAccount(1, 1140.25);
        savingAccount2 = new SavingIAccount(1, 1140.25);

        checkingAccount1 = new CheckingIAccount(2, 1120.75, 100.0);
        checkingAccount2 = new CheckingIAccount(2, 1120.75, 100.0);

    }

    @Test
    public void testEqualAccounts() throws NotEnoughFundsException {
        assertTrue(savingAccount1.equals(savingAccount2));
        assertFalse(savingAccount1 == savingAccount2);

        assertTrue(checkingAccount1.equals(checkingAccount2));
        assertFalse(checkingAccount1 == checkingAccount2);

        SavingIAccount savingAccount3 = new SavingIAccount(3, 1140.25);
        assertFalse(savingAccount1.equals(savingAccount3));
        assertFalse(savingAccount1 == savingAccount3);

        CheckingIAccount checkingAccount4 = new CheckingIAccount(4, 1120.75, 100.0);
        assertFalse(checkingAccount1.equals(checkingAccount4));
        assertFalse(checkingAccount1 == checkingAccount4);
    }

    @Test
    public void testAccountsToString() throws NotEnoughFundsException {
        assertEquals("Saving account 1, balance: 1140.25", savingAccount1.toString());
        assertEquals("Checking account 2, balance: 1120.75, overdraft: 100.00", checkingAccount1.toString());
    }

    @Test
    public void testAccountsToDecimal() throws NotEnoughFundsException {
        assertEquals(1140, savingAccount1.decimalValue());
        assertEquals(1121, checkingAccount1.decimalValue());
    }
}
