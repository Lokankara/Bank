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
 * 1) It is necessary to implement hashCode () and equals ()
 * In the EmployeeByAge, EmployeeByFullName
 * 2) Implement hashCode () and equals () for the Employee class,
 * Which will compare all fields (name, surname, age).
 */
public class ObjectMethodsTutor {

    public ObjectMethodsTutor.Employee createEmployee(ObjectMethodsTutor.EmployeeType type, String name, String surname, int age) {
        switch (type) {
            case ByName:
                return new ObjectMethodsTutor.EmployeeByName(name, surname, age);
            case ByAge:
                return new ObjectMethodsTutor.EmployeeByAge(name, surname, age);
            case ByFullName:
                return new ObjectMethodsTutor.EmployeeByFullName(name, surname, age);
            case Default:
                break;
            default:
                break;
        }
        return new ObjectMethodsTutor.Employee(name, surname, age);
    }

    public List<ObjectMethodsTutor.Employee> getEmployees(ObjectMethodsTutor.EmployeeType type) {
        List<ObjectMethodsTutor.Employee> employees = new ArrayList<ObjectMethodsTutor.Employee>();
        employees.add(createEmployee(type, "Ivan", "Ivanov", 35));
        employees.add(createEmployee(type, "Ivan", "Ivanov", 30));
        employees.add(createEmployee(type, "Ivan", "Petrov", 25));
        employees.add(createEmployee(type, "Ivan", "Sidorov", 25));
        return employees;
    }

    @Test
    public void testEmployees() {
        List<ObjectMethodsTutor.Employee> employees = getEmployees(ObjectMethodsTutor.EmployeeType.Default);
        int index = 0;
        for (ObjectMethodsTutor.Employee employee : employees) {
            log("emp" + index++ + "=" + employee);
        }

        log("***Testing equals: EmployeeByName***");
        employees = getEmployees(ObjectMethodsTutor.EmployeeType.ByName);
        assertEquals(employees.get(0), employees.get(1));
        assertEquals(employees.get(1), employees.get(2));
        assertEquals(employees.get(2), employees.get(3));
        for (int i = 0; i < 3; i++) {
            log("emp" + i + ".equals(emp" + (i + 1) + ")=" +
                    employees.get(i).equals(employees.get(i + 1)));
        }

        log("***Testing equals: EmployeeByAge***");
        employees = getEmployees(ObjectMethodsTutor.EmployeeType.ByAge);
        assertNotSame(employees.get(0), employees.get(1));
        assertNotSame(employees.get(1), employees.get(2));
        assertEquals(employees.get(2), employees.get(3));
        for (int i = 0; i < 3; i++) {
            log("emp" + i + ".equals(emp" + (i + 1) + ")=" +
                    employees.get(i).equals(employees.get(i + 1)));
        }

        log("***Testing equals: EmployeeByFullName***");
        employees = getEmployees(ObjectMethodsTutor.EmployeeType.ByFullName);
        assertEquals(employees.get(0), employees.get(1));
        assertNotSame(employees.get(1), employees.get(2));
        assertNotSame(employees.get(2), employees.get(3));
        for (int i = 0; i < 3; i++) {
            log("emp" + i + ".equals(emp" + (i + 1) + ")=" +
                    employees.get(i).equals(employees.get(i + 1)));
        }
    }

    public Set<ObjectMethodsTutor.Employee> getEmployeesSet(ObjectMethodsTutor.EmployeeType type) {
        Set<ObjectMethodsTutor.Employee> employeesSet = new HashSet<ObjectMethodsTutor.Employee>();
        employeesSet.addAll(getEmployees(type));
        return employeesSet;
    }

    @Test
    public void testEmployeesSet() {
        Set<ObjectMethodsTutor.Employee> employeesSet;
        log("***Testing HashSet: EmployeeByName");
        employeesSet = getEmployeesSet(ObjectMethodsTutor.EmployeeType.ByName);
        for (ObjectMethodsTutor.Employee employee : employeesSet) {
            log(employee);
        }
        assertEquals(employeesSet.size(), 1);

        log("***Testing HashSet: EmployeeByAge");
        employeesSet = getEmployeesSet(ObjectMethodsTutor.EmployeeType.ByAge);
        for (ObjectMethodsTutor.Employee employee : employeesSet) {
            log(employee);
        }
        assertEquals(employeesSet.size(), 3);

        log("***Testing HashSet: EmployeeByFullName");
        employeesSet = getEmployeesSet(ObjectMethodsTutor.EmployeeType.ByFullName);
        for (ObjectMethodsTutor.Employee employee : employeesSet) {
            log(employee);
        }
        assertEquals(employeesSet.size(), 3);

        log("***Testing HashSet: the hashCode and equals by default");
        employeesSet = getEmployeesSet(ObjectMethodsTutor.EmployeeType.Default);
        for (ObjectMethodsTutor.Employee employee : employeesSet) {
            log(employee);
        }
        assertEquals(employeesSet.size(), 4);

    }

    enum EmployeeType {
        ByName, ByFullName, ByAge, Default
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

        public String toString() {
            return this.name + " " + this.surname + ", Age:" + age;
        }
    }

    class EmployeeByName extends ObjectMethodsTutor.Employee {
        public EmployeeByName(String name, String surname, int age) {
            super(name, surname, age);
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof ObjectMethodsTutor.Employee) {
                ObjectMethodsTutor.Employee emp = (ObjectMethodsTutor.Employee) obj;
                return emp.name.equals(this.name);
            }
            return super.equals(obj);
        }
    }

    class EmployeeByFullName extends ObjectMethodsTutor.Employee {
        public EmployeeByFullName(String name, String surname, int age) {
            super(name, surname, age);
        }

        public String getFullName() {
            return this.name + " " + this.surname;
        }

        public int hashCode() {
            return getFullName().hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof ObjectMethodsTutor.EmployeeByFullName) {
                ObjectMethodsTutor.EmployeeByFullName emp = (ObjectMethodsTutor.EmployeeByFullName) obj;
                return emp.getFullName().equals(this.getFullName());
            }
            return super.equals(obj);
        }
    }

    class EmployeeByAge extends ObjectMethodsTutor.Employee {
        public EmployeeByAge(String name, String surname, int age) {
            super(name, surname, age);
        }

        public int hashCode() {
            return this.age;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ObjectMethodsTutor.Employee) {
                ObjectMethodsTutor.Employee emp = (ObjectMethodsTutor.Employee) obj;
                return emp.age == this.age;
            }
            return super.equals(obj);
        }
    }
}
