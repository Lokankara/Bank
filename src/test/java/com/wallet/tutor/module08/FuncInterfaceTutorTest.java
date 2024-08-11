package com.wallet.tutor.module08;

import com.wallet.bank.domain.Gender;
import com.wallet.tutor.Logger;
import com.wallet.tutor.module08.defaultInterfaces.Comparator;
import com.wallet.tutor.module08.defaultInterfaces.Consumer;
import com.wallet.tutor.module08.defaultInterfaces.FileFilter;
import com.wallet.tutor.module08.defaultInterfaces.Function;
import com.wallet.tutor.module08.defaultInterfaces.PersonPredicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static com.wallet.tutor.module08.LambdaTest.transferElements;
import static com.wallet.tutor.module08.defaultInterfaces.PersonPredicate.toJavaPredicate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class FuncInterfaceTutorTest {
    private static int max = 5;
    private static final String suffix = ".java";
    private static Person[] rosterAsArray;
    private static Collection<Person> roster;

    @BeforeEach
    public void init() {
        LocalDate birthdaySteve = LocalDate.of(1980, 3, 4);
        Person steve = new Person("Steve Rogers", "steve.rogers@avengers.com", Gender.MALE, birthdaySteve);

        LocalDate birthdayTony = LocalDate.of(1970, 5, 29);
        Person tony = new Person("Tony Stark", "tony.stark@avengers.com", Gender.MALE, birthdayTony);

        LocalDate birthdayJanuary = LocalDate.of(1990, 1, 15);
        LocalDate birthdayMay = LocalDate.of(1992, 5, 10);
        LocalDate birthdayJune = LocalDate.of(1995, 6, 20);
        Person january = new Person("January Person", "january@avengers.com", Gender.FEMALE, birthdayJanuary);
        Person may = new Person("May Person", "may@avengers.com", Gender.FEMALE, birthdayMay);
        Person june = new Person("June Person", "june@avengers.com", Gender.FEMALE, birthdayJune);

        rosterAsArray = new Person[]{june, may, january, steve, tony};
        roster = List.of(rosterAsArray);
    }

    Runnable runnable = () -> log.info("Hello World!");

    @Test
    @DisplayName("runnable instance as lambda expression")
    public void shouldKnowValidateRunnableInstance() {
        assertInstanceOf(Runnable.class, runnable);
    }

    @Test
    @DisplayName(" as lambda expression")
    public void peek() {
    }

    @Test
    @DisplayName("how to get a string using supplier")
    public void shouldSupplyAnString() {
        Supplier<String> supplier = () -> "supplier";
        assertEquals("supplier", supplier.get());
    }

    @Test
    @DisplayName("how to get string length")
    public void shouldGetStringLength() {
        Integer expectedResult = 6;
        Function<String, Integer> function = String::length;
        assertEquals(expectedResult, function.apply("length"));
    }

    @Test
    @DisplayName("how to get length length and if it is even")
    public void shouldKnowIfNicknameLengthIsEven() {
        Function<String, Integer> lengthFunction = String::length;
        Function<Integer, Boolean> evenFunction = i -> i % 2 == 0;
        assertTrue(lengthFunction.andThen(evenFunction).apply("length"));
    }

    @Test
    @DisplayName("how to get function identity")
    public void shouldGetFunctionIdentity() {
        Function<String, String> function = Function.identity();
        assertEquals("length", function.apply("length"));
    }


    @Test
    @DisplayName("how to get persons high age and starts with J")
    public void shouldGetPersonsHighAgeAndStartsWithA() {
        log.info(roster.toString());
        PersonPredicate<Person> isAdult = person -> person.getAge() >= 18;
        PersonPredicate<Person> startsWith = person -> person.getName().startsWith("J");

        long count = roster.stream()
                .filter(toJavaPredicate(isAdult))
                .filter(toJavaPredicate(startsWith))
                .count();

        assertEquals(2, count);
    }

    @Test
    @DisplayName("how to get persons with four in ranking or more")
    public void shouldGetPersonsWithFourInRankingOrMore() {
        PersonPredicate<Person> isAdult = person -> person.getAge() >= 18;

        long count = roster.stream()
                .filter(toJavaPredicate(isAdult))
                .count();

        assertEquals(5, count);
    }

    @Test
    public void testImplStatic() {
        List<String> result = new ArrayList<>();

        Consumer<String> consumer = Logger::log;
        consumer.accept("String");

        Arrays.sort(rosterAsArray, Person::compareTo);

        Arrays.stream(rosterAsArray).map(Person::getName).forEach(consumer::accept);

        String[] stringArray = {"Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda"};

        Arrays.sort(stringArray, String::compareToIgnoreCase);

        Arrays.stream(stringArray).forEach(consumer::accept);

        Set<Person> rosterSetLambda = transferElements(roster, HashSet::new);

        for (Person person : rosterSetLambda) {
            consumer.accept(person.getBirthday().toString());
        }

    }

    @Test
    void testInterfaces() {

        PersonPredicate<Integer> isAdult = age -> age >= 18;
        Logger.log(isAdult.test(10));

        Supplier<String> hi = () -> "hi";

        Logger.log(hi.get());

        Consumer<String> printer = p -> Logger.log(String.format("Printed %s", p));

        printer.accept(hi.get());

        Comparator<String> comparator = (String a, String b) -> Integer.compare(a.length(), b.length());
        Comparator<String> compare = (a, b) -> Integer.compare(a.length(), b.length());

        Logger.log(compare.compare("compare.comparator.compare", "comparator.compare"));
        Logger.log(comparator.compare("comparator.compare", "comparator.compare.comparator"));

        Function<String, String> function = String::toLowerCase;
        function.apply(hi.get());

        Comparator<Integer> integerComparator = Integer::compare;
        Logger.log(integerComparator.compare(30, 50));
    }

    @Test
    void testFileFilter() {
        FileFilter fileFilter = pathname -> pathname.getName().endsWith(suffix);
        FileFilter filter = (File file) -> file.getName().endsWith(suffix);

        Runnable r = () -> {
            for (int i = 0; i < max; i++) {
                log.info(suffix);
            }
        };

        r.run();
    }
}