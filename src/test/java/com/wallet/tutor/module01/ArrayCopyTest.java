package com.wallet.tutor.module01;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * This class does the same as the standard ArrayList does:
 * Increases the size of the array when the array is full.
 * Implement the deleteAnimal (int position)
 */
@Slf4j
public class ArrayCopyTest {
    private int animalsCapacity = 5;
    private static final int animalsExpandBy = 5;
    private int animalsSize = 0;
    String[] animals = new String[animalsCapacity];

    public void addAnimal(String animal) {

        if (0 > animalsSize || animalsSize >= animalsCapacity) {
            expandAnimalsArray();
        }
        animals[animalsSize++] = animal;
    }

    private void expandAnimalsArray() {
        animalsCapacity += animalsExpandBy;
        animals = Arrays.copyOf(animals, animalsCapacity);
    }

    public void insertAnimal(int position, String animal) {
        if (0 > position || position > animalsSize - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (position == animalsSize - 1) {
            addAnimal(animal);
        }
        if (animalsSize >= animalsCapacity) {
            expandAnimalsArray();
        }
        System.arraycopy(animals, position, animals, position + 1, ++animalsSize - position - 1);

        animals[position] = animal;

    }

    public void deleteAnimal(int position) {
        if (0 <= position && position <= --animalsSize) {
            System.arraycopy(animals, position + 1, animals, position, animalsSize - position + 1);
        }
    }

    public void showAnimals() {
        for (int i = 0; i < animalsSize; i++) {
            log.info(String.format("%d: %s", i, animals[i]));
        }
    }

    @Test
    public void testAnimals() {
        addAnimal("Horse");
        addAnimal("Rhino");
        addAnimal("Dog");
        addAnimal("Snake");
        addAnimal("Monkey");
        addAnimal("Turkey");
        addAnimal("Roe");
        addAnimal("Lion");
        addAnimal("Tiger");
        addAnimal("Cat");
        addAnimal("Turtle");
        insertAnimal(1, "Human");
        deleteAnimal(2);
        showAnimals();
        Assertions.assertArrayEquals(new String[]{
                "Horse",
                "Human",
                "Dog",
                "Snake",
                "Monkey",
                "Turkey",
                "Roe",
                "Lion",
                "Tiger",
                "Cat",
                "Turtle",
                null, null, null, null
        }, animals);
    }
}
