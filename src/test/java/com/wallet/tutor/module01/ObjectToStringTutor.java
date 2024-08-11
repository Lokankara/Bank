package com.wallet.tutor.module01;

import static com.wallet.tutor.Logger.log;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ObjectToStringTutor {

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Ivan", "Ivanov", 35));
        employees.add(new Employee("Ivan", "Ivanov", 30));
        employees.add(new Employee("Ivan", "Petrov", 25));
        employees.add(new Employee("Ivan", "Sidorov", 25));
        return employees;
    }

    @Test
    public void testEmployees() {
        List<Employee> employees = getEmployees();
        int index = 0;
        for (Employee employee : employees) {
            log("emp" + index++ + "=" + employee);
            assertTrue(employee.toString().contains("age"));
        }
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

        @Override
        public String toString() {
            return "Employee:{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
