package com.wallet.tutor.module01;
import java.util.ArrayList;
import java.util.List;

import com.wallet.bank.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ObjectToStringTest {

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
            log.info("emp" + index++ + "=" + employee);
        }
    }
}
