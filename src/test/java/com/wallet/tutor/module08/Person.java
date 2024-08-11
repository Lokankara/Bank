package com.wallet.tutor.module08;

import com.wallet.bank.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;

@Getter
@ToString
@AllArgsConstructor
public class Person implements Comparable<Person> {
    @Override
    public int compareTo(Person p) {
        return birthday.compareTo(p.birthday);
    }

    private int age;
    private String name;
    private String email;
    private Gender gender;
    private final LocalDate birthday;

    public Person(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Person(String name, String email, Gender gender, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public int getAge() {
        return calculateAge(birthday);
    }
}
