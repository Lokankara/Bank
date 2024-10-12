package com.wallet.tutor.module05;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CollectionUtilitiesTutor {
    String[] animals =
            {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};

    public void print(Collection<?> c) {
        StringBuilder builder = new StringBuilder();
        for (Object o : c) {
            builder.append(o).append(" ");
        }
        log.info(builder.toString());
    }

    @Test
    public void testUtilities() {
        List<String> list = Arrays.asList(animals);
        log.info("== print the resulting list");
        print(list);

        log.info("== print the shuffled list");
        Collections.shuffle(list);
        print(list);

        log.info("== print the sorted list (used natural ordering)");
        Collections.sort(list);
        print(list);

        log.info("== binary Search of Elephant after sorting: " + Collections.binarySearch(list, "Elephant"));

        log.info("== print the reversed list");
        Collections.reverse(list);
        print(list);

        log.info("== binary Search of Elephant without sorting: " + Collections.binarySearch(list, "Elephant"));

        log.info("== print the sorted by length list of word");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        print(list);

        log.info("== max (used natural ordering): " + Collections.max(list));
        log.info("== min (used natural ordering): " + Collections.min(list));

        log.info("== frequency of Cow: " + Collections.frequency(list, "Cow"));
        log.info("== frequency of Human: " + Collections.frequency(list, "Human"));

        log.info("== replace Cow to Pig: ");
        Collections.replaceAll(list, "Cow", "Pig");
        print(list);

        log.info("== swap: swap first and last values: ");
        Collections.swap(list, 0, list.size() - 1);
        print(list);

        log.info("== rotate: rotate by 2: ");
        Collections.rotate(list, 2);
        print(list);

        log.info("== indexOfSubList: look for sublist in the list ");
        List<String> subList = Arrays.asList(new String[]{list.get(5), list.get(6)});
        print(subList);
        log.info("sublist position: " + Collections.indexOfSubList(list, subList));

        log.info("== fill: fill list with the same values: ");
        Collections.fill(list, ".");
        print(list);
    }

}
