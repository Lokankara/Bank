package com.wallet.tutor.module05;

import static com.wallet.tutor.Logger.log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.wallet.tutor.Logger;
import org.junit.jupiter.api.Test;

/**
 * Implement method Set<Animal> getAnimalsOrderedByNameSet()
 * and method Set<Animal> getAnimalsOrderedByNameSetDesc()
 */

public class ComparableTutor {
    String[] animals =
            {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};

    static class Animal implements Comparable<Animal> {
        String name;

        public Animal(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(Animal animal) {
            return name.compareTo(animal.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Animal)) return false;
            Animal animal = (Animal) o;
            return Objects.equals(name, animal.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    /**
     * Method should return a Set of Animal instances, alphabetically sorted
     * Use TreeSet for that and implement Comparable interface in Animal class.
     */
    public Set<Animal> getAnimalsOrderedByNameSet() {
        return Arrays.stream(animals)
                .map(Animal::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Method should return a Set of Animal instances,
     * ordered by the name in reverse order.
     * Use TreeSet for that and pass a Comparator implementation as a parameter.
     */
    public Set<Animal> getAnimalsOrderedByNameSetDesc() {
        return Arrays.stream(animals)
                .map(Animal::new)
                .collect(Collectors.toCollection(TreeSet::new))
                .descendingSet();
    }

    public String joinByCycle(Collection<?> c) {
        if (c == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        c.forEach(s -> build(builder, s));
        return builder.toString();
    }

    private static void build(StringBuilder builder, Object s) {
        builder.append(s);
        if (builder.length() > 0) {
            builder.append(", ");
        }
    }

    @Test
    public void testCollections() {
        log("getAnimalsList: " + joinByCycle(Arrays.asList(animals)));
        log("getAnimalsOrderedByNameSet: " + joinByCycle(getAnimalsOrderedByNameSet()));
        log("getAnimalsOrderedByNameSetDesc: " + joinByCycle(getAnimalsOrderedByNameSetDesc()));
    }

    @Test
    public void getAnimalsOrderedByNameSet_default_returnsSortedSet() {
        Set<Animal> set = getAnimalsOrderedByNameSet();
        String join = joinByCycle(set);
        assertEquals("Cat, Chicken, Cow, Dog, Elephant, Goose, Horse, Human, Rabbit, Snake, ", join);
    }

    @Test
    public void getAnimalsOrderedByNameSetDesc_default_returnsSortedSet() {
        Set<Animal> set = getAnimalsOrderedByNameSetDesc();
        String join = joinByCycle(set);
        assertEquals("Snake, Rabbit, Human, Horse, Goose, Elephant, Dog, Cow, Chicken, Cat, ", join);
    }
}
