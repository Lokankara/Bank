package com.wallet.tutor.module01;

import static com.wallet.tutor.Logger.log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import org.junit.jupiter.api.Test;

/**
 * Implement the hashSet() and equals() methods in the Employee class,
 * To compare the full name, but not the age of the employee.
 */
public class ObjectEqualsTutor {
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Ivan", "Ivanov", 35));
        employees.add(new Employee("Ivan", "Ivanov", 30));
        employees.add(new Employee("Ivan", "Petrov", 25));
        employees.add(new Employee("Ivan", "Sidorov", 25));
        return employees;
    }

    public Set<Employee> getEmployeesSet(ObjectMethodsTutor.EmployeeType type) {
        Set<Employee> employeesSet = new HashSet<Employee>();
        employeesSet.addAll(getEmployees());
        return employeesSet;
    }

    @Test
    public void testEmployees() {
        List<Employee> employees = getEmployees();
        int index = 0;
        for (Employee employee : employees) {
            log("emp" + index++ + "=" + employee);
        }

        log("***Testing equals: ***");
        employees = getEmployees();
        for (int i = 0; i < 3; i++) {
            log("emp" + i + ".equals(emp" + (i + 1) + ")=" +
                    employees.get(i).equals(employees.get(i + 1)));
        }
        assertEquals(employees.get(0), employees.get(1));
        assertNotSame(employees.get(1), employees.get(2));
        assertNotSame(employees.get(2), employees.get(3));
    }

    @Test
    public void testEmployeesSet() {
        Set<Employee> employeesSet;
        log("***Testing HashSet: Employee");
        employeesSet = getEmployeesSet(ObjectMethodsTutor.EmployeeType.ByName);
        for (Employee employee : employeesSet) {
            log(employee);
        }
        assertEquals(employeesSet.size(), 3);
    }

    class Employee {
        public String name;
        public String surname;
        public int age;

        public Employee(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }

        public String getFullName() {
            return String.format("%s %s", this.name, this.surname);
        }

        public String toString() {
            return String.format("%s %s, Age:%d", this.name, this.surname, age);
        }

        public boolean equals(Object o) {
            if (!(o instanceof Employee)) {
                return false;
            }
            return ((Employee) o).getFullName().equals(this.getFullName());
        }

        @Override
        public int hashCode() {
            return getFullName().hashCode();
        }
    }
}
