package com.wallet.tutor.module01;

import com.wallet.bank.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Implement the hashSet() and equals() methods in the Employee class,
 * To compare the full name, but not the age of the employee.
 */
@Slf4j
public class ObjectEqualsTest {
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ivan", "Ivanov", 35));
        employees.add(new Employee("Ivan", "Ivanov", 30));
        employees.add(new Employee("Ivan", "Petrov", 25));
        employees.add(new Employee("Ivan", "Sidorov", 25));
        return employees;
    }

    public Set<Employee> getEmployeesSet(ObjectMethodsTest.EmployeeType type) {
        Set<Employee> employeesSet = new HashSet<Employee>();
        employeesSet.addAll(getEmployees());
        return employeesSet;
    }

    @Test
    public void testEmployees() {
        List<Employee> employees = getEmployees();
        int index = 0;
        for (Employee employee : employees) {
            log.info("emp" + index++ + "=" + employee);
        }

        log.info("***Testing equals: ***");
        employees = getEmployees();
        for (int i = 0; i < 3; i++) {
            log.info("emp" + i + ".equals(emp" + (i + 1) + ")=" +
                    employees.get(i).equals(employees.get(i + 1)));
        }
        assertEquals(employees.get(0), employees.get(1));
        assertNotSame(employees.get(1), employees.get(2));
        assertNotSame(employees.get(2), employees.get(3));
    }

    @Test
    public void testEmployeesSet() {
        Set<Employee> employeesSet;
        log.info("***Testing HashSet: Employee");
        employeesSet = getEmployeesSet(ObjectMethodsTest.EmployeeType.ByName);
        for (Employee employee : employeesSet) {
            log.info(employee.toString());
        }
        assertEquals(employeesSet.size(), 3);
    }
}
