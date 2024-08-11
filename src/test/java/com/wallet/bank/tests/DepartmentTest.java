package com.wallet.bank.tests;

import com.wallet.bank.domain.Department;
import com.wallet.bank.employees.BankEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class DepartmentTest {

    @Test
    public void testManager() {
        Department department = new Department("Accounting");
        BankEmployee richard = new BankEmployee("Richard", department, 1000);
        BankEmployee bob = new BankEmployee("Bob", department, 1000);
        department.setManager(richard);
        assertEquals(bob.getDepartment().getManager().getName(), "Richard");
    }
}
