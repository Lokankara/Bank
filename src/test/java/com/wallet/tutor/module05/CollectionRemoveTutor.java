package com.wallet.tutor.module05;

import static com.wallet.tutor.Logger.log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.wallet.tutor.Logger;
import org.junit.jupiter.api.Test;

/**
 * 1) Remove all entrances of word "Cow" and print the result
 * 2) Remove all entrances having 3 letters and print the result
 * 3) Implement method removeIf which will take interface ShouldRemove as a parameter:
 * <T> List<T> removeIf(List<T> list, ShouldRemove<T> shouldRemove)
 * This method have to iterate over the list and
 * remove those elements for which ShouldRemove.check() returns true
 */


public class CollectionRemoveTutor {
    String[] animals = {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};

    public String joinByCycle(Collection<?> c) {
        StringBuilder builder = new StringBuilder();
        for (Object item : c) {
            builder.append(item);
            if (builder.length() > 0) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public List<String> getAnimals() {
        return new ArrayList<>(Arrays.asList(animals));
    }

    // Remove all entrances of word "Cow"
    public void unCow(List<String> list) {
        while (list.remove("Cow")) ;
    }


    // Remove all entrances having 3 letters
    public void un3Letterization(List<String> list) {
        list.removeIf(animal -> animal.length() < 4);
    }


    @Test
    public void testRemove() {
        List<String> list;

        list = getAnimals();
        unCow(list);
        log("list after remove: " + joinByCycle(list));

        list = getAnimals();
        un3Letterization(list);
        log("list after remove 3 letters animals: " + joinByCycle(list));
    }

    @Test
    public void unCow_emptyList_doNothing() {
        List<String> list = new ArrayList<>();

        unCow(list);

        assertTrue(list.isEmpty());
    }

    @Test
    public void unCow_1CowList_removeCowFromList() {
        List<String> list = new ArrayList<>();

        list.add("Cow");

        unCow(list);

        assertTrue(list.isEmpty());
    }

    @Test
    public void unCow_2CowAndAnyStringList_removeKorovaFromList() {
        List<String> list = new ArrayList<>();

        list.add("Cow");
        list.add("anystring");
        list.add("Cow");

        unCow(list);

        assertEquals(1, list.size());
        assertEquals("anystring", list.get(0));
    }

    @Test
    public void un3Letterization_emptyList_doNothing() {
        List<String> list = new ArrayList<String>();

        un3Letterization(list);

        assertTrue(list.isEmpty());
    }

    @Test
    public void un3Letterization_1FourLettersWordList_removeFourLettersWordFromList() {
        List<String> list = new ArrayList<String>();

        list.add("333");

        un3Letterization(list);

        assertTrue(list.isEmpty());
    }

    @Test
    public void deFourLetterization_2FourLettersWordsAndAnyStringList_removeFourLettersWordsFromList() {
        List<String> list = new ArrayList<String>();

        list.add("333");
        list.add("anystring");
        list.add("333");

        un3Letterization(list);

        assertEquals(1, list.size());
        assertEquals("anystring", list.get(0));
    }

}
