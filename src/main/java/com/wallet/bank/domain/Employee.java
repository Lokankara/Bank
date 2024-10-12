package com.wallet.bank.domain;

public class Employee {
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