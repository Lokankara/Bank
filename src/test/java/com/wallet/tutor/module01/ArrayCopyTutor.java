package com.wallet.tutor.module01;

import com.wallet.tutor.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * This class does the same as the standard ArrayList does:
 * Increases the size of the array when the array is full.
 * <p>
 * Implement the deleteAnimal (int position)
 */
public class ArrayCopyTutor {
    private int animals_capacity = 5;
    private static final int animals_expand_by = 5;
    private int animals_size = 0;
    String[] animals = new String[animals_capacity];

    public void addAnimal(String animal) {

        if (0 > animals_size || animals_size >= animals_capacity) {
            expandAnimalsArray();
        }
        animals[animals_size++] = animal;
    }

    private void expandAnimalsArray() {
        animals_capacity += animals_expand_by;
        animals = Arrays.copyOf(animals, animals_capacity);
    }

    public void insertAnimal(int position, String animal) {
        if (0 > position || position > animals_size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (position == animals_size - 1) {
            addAnimal(animal);
        }
        if (animals_size >= animals_capacity) {
            expandAnimalsArray();
        }
        System.arraycopy(animals, position, animals, position + 1, ++animals_size - position - 1);

        animals[position] = animal;

    }

    public void deleteAnimal(int position) {
        if (0 <= position && position <= --animals_size) {
            System.arraycopy(animals, position + 1, animals, position, animals_size - position + 1);
        }
    }

    public void showAnimals() {
        for (int i = 0; i < animals_size; i++) {
            Logger.log(String.format("%d: %s", i, animals[i]));
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
