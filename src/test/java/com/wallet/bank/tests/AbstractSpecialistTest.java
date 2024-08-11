package com.wallet.bank.tests;

import com.wallet.bank.employees.AbstractSpecialist;
import com.wallet.bank.employees.FinancialSpecialist;
import com.wallet.bank.employees.HousingPropertySpecialist;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AbstractSpecialistTest {

    private static AbstractSpecialist getChainOfSpecialists() {
        AbstractSpecialist financialSpecialist = new FinancialSpecialist();
        AbstractSpecialist housingPropertySpecialist = new HousingPropertySpecialist();
        financialSpecialist.setNextSpecialist(housingPropertySpecialist);
        return financialSpecialist;
    }

    @Test
    public void testAnalysis() {
        AbstractSpecialist specialistsChain = getChainOfSpecialists();
        assertEquals(200.0, AbstractSpecialist.calculateAnalysisCommission(100000.0, true), 0);
        assertEquals(1000.0, AbstractSpecialist.calculateAnalysisCommission(200000.0, false), 0);
        assertFalse(specialistsChain.makeDecision(100000.0, 1000.00, 2600.00, true));
        assertFalse(specialistsChain.makeDecision(100000.0, 1000.00, 3600.00, false));
        assertTrue(specialistsChain.makeDecision(100000.0, 1000.00, 3600.00, true));
    }
}
