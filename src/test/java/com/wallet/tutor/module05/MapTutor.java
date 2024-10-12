package com.wallet.tutor.module05;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1) Implement methods fillAnimalsLengthMap() and printMap().
 * Look at the result of the program execution.
 * 2) Implement methods fillLengthAnimalsMap() and printMapOfSets()
 * Look at the result of the program execution.
 */
@Slf4j
public class MapTutor {
    Map<String, Integer> animalsLengthMap = new HashMap<String, Integer>();
    Map<Integer, Set<String>> lengthAnimalsMap = new HashMap<Integer, Set<String>>();

    String[] animals =
            {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};

    /**
     * should fill the table animalsLengthMap with values
     * animal => animal.length()
     * for example
     * "Cow" => 3
     * "Snake" => 5
     */
    public void fillAnimalsLengthMap() {

        Arrays.stream(animals)
                .forEach(animal ->
                        animalsLengthMap.put(animal, animal.length()));
    }

    /**
     * Prints the table animalsLengthMap,
     * by printing the key and value
     */
    public void printMap(Map<?, ?> map) {
        map.keySet().stream()
                .map(key -> String.format("%s:%s", key, map.get(key)))
                .forEach(log::info);
    }

    /**
     * Fills table lengthAnimalsMap by values
     * animal name length => list of the animals of such a length
     * for example:
     * 3 => Cow, Dog, Cat
     * 5 => Goose, Snake, Horse, Human
     * 6 => Rabbit
     */
    public void fillLengthAnimalsMap() {
        Arrays.stream(animals).forEach(this::push);
    }

    private void push(String animal) {
        Set<String> set = lengthAnimalsMap.computeIfAbsent(animal.length(), k -> new HashSet<>());
        set.add(animal);
        animalsLengthMap.put(animal, animal.length());
    }

    /**
     * prints the contents of lengthAnimalsMap,
     * by printing the key and list of values
     */
    public void printMapOfSets(Map<Integer, Set<String>> map) {
        printMap(map);
    }

    @Test
    public void testMap() {
        fillAnimalsLengthMap();
        log.info("== printMap animalsLengthMap");
        printMap(animalsLengthMap);

        log.info("== printMap treemap animalsLengthMap");
        SortedMap<String, Integer> sortedMap = new TreeMap<String, Integer>(animalsLengthMap);
        printMap(sortedMap);

        log.info("== print lengthAnimalsMap");
        fillLengthAnimalsMap();
        printMapOfSets(lengthAnimalsMap);

        SortedMap<Integer, Set<String>> sortedMap2 = new TreeMap<Integer, Set<String>>(lengthAnimalsMap);

        log.info("== sortedMap headSet where key<6");
        printMapOfSets(sortedMap2.headMap(6));

        log.info("== sortedMap subMap 5..7");
        printMapOfSets(sortedMap2.subMap(5, 7));
    }

    @Test
    public void fillAnimalsLengthMap_default_fillsAnimalsLengthMapWithData() {
        animalsLengthMap.clear();
        fillAnimalsLengthMap();
        assertEquals(3, (int) animalsLengthMap.get("Cow"));
        assertEquals(5, (int) animalsLengthMap.get("Goose"));
    }

    @Test
    public void fillLengthAnimalsMap_default_fillLengthAnimalsMapWithData() {
        lengthAnimalsMap.clear();
        fillLengthAnimalsMap();
        assertTrue(lengthAnimalsMap.get(3).contains("Cow"));
        assertTrue(lengthAnimalsMap.get(5).contains("Goose"));
    }
}
