package com.wallet.tutor.module09;

import com.wallet.tutor.module09.model.Gender;
import com.wallet.tutor.module09.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * 1) Create a map which prints amount of person of each age
 * use stream.collect(Collectors.groupingBy(Function, Collectors.mapping(Function, Collectors.counting())))
 * 2) Create a map Gender->String with gender as keys and comma-separated names as values
 * use stream.collect(Collectors.groupingBy(Function, Collectors.mapping(Function, Collectors.joining(","))))
 * 3) Calculate and print the average age of females
 * use stream.filter(Predicate).mapToInt(Function).average()
 * 4) Create a map with genders as keys and average ages as values
 * use stream.collect(Collectors.groupingBy(Function, Collectors.averagingInt(Function))
 * 5) Print the list of persons in alphabetical order of names
 * use stream.sorted(Comparator.comparing(Function))
 * 6) Print the list of persons in order of gender, then name:
 * use stream.sorted(Comparator.comparing(Function).thenComparing(Function)
 */
@Slf4j
public class CollectorsTutor {

    public static void main(String... args) {
        List<Person> persons = new ArrayList<>();
        try (
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        Objects.requireNonNull(
                                                CollectorsTutor.class.getResourceAsStream("people.txt"))));
                Stream<String> stream = reader.lines();
        ) {

            stream.map(line -> {
                        String[] s = line.split(" ");
                        Person p = new Person(s[0].trim(), Integer.parseInt(s[1]), s[2].trim());
                        persons.add(p);
                        return p;
                    })
                    .forEach(System.out::println);


        } catch (IOException ioe) {
            log.info(ioe.getMessage());
        }

        // Find a person with a minimal age more than 20
        Optional<Person> opt =
                persons.stream().filter(p -> p.getAge() >= 20)
                        .min(Comparator.comparing(Person::getAge));
        log.info(opt.toString());

        // Find a person with a maximal age 
        Optional<Person> opt2 = persons.stream().max(Comparator.comparing(Person::getAge));
        log.info(opt2.toString());

        // Group persons by their ages 
        Map<Integer, String> map = persons.stream()
                .collect(Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(Person::getName, joining(", "))
                        )
                );

        // 1) Create a map which prints amount of person of each age
        //  use stream.collect(Collectors.groupingBy(Function, Collectors.mapping(Function, Collectors.counting())))
        Map<Integer, Long> collect = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        log.info(String.valueOf(collect));

        // 2) Create a map Gender->String with gender as keys and comma-separated names as values
        // 	use stream.collect(Collectors.groupingBy(Function, Collectors.mapping(Function, Collectors.joining(","))))
        Map<Gender, String> byGender = persons.stream()
                .collect(Collectors.groupingBy(Person::getGender,
                        Collectors.mapping(Person::getName, joining(","))));
        log.info(String.valueOf(byGender));

        // 3) Calculate and print the average age of females
        // 	use stream.filter(Predicate).mapToInt(Function).average()
        double average = persons.stream()
                .filter(x -> x.getGender().equals(Gender.Female))
                .mapToInt(Person::getAge)
                .average()
                .orElseThrow();

        log.info(String.valueOf(average));

        // 4) Create a map with genders as keys and average ages as values
        // 	use stream.collect(Collectors.groupingBy(Function, Collectors.averagingInt(Function))
        Map<Gender, Double> genders = persons.stream()
                .collect(Collectors.groupingBy(
                        Person::getGender,
                        Collectors.averagingInt(Person::getAge)));

        log.info(String.valueOf(genders));

        // 5) Print the list of persons in alphabetical order of names
        List<Person> abc = persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .toList();

        log.info(String.valueOf(abc));

        // 6) Print the list of persons in order of gender, then name:
        // 	use stream.sorted(Comparator.comparing(Function).thenComparing(Function)
        List<Person> sorted = persons.stream()
                .sorted(Comparator.comparing(Person::getGender)
                        .thenComparing(Person::getName))
                .toList();

        log.info(String.valueOf(sorted));
    }
}
