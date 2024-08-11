package com.wallet.tutor.module05;

import static com.wallet.tutor.Logger.log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wallet.tutor.Logger;
import org.junit.jupiter.api.Test;

public class CollectionUtilitiesTutor {
    String[] animals =
            {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};

    public void print(Collection<?> c) {
        StringBuilder builder = new StringBuilder();
        for (Object o : c) {
            builder.append(o).append(" ");
        }
        log(builder.toString());
    }

    @Test
    public void testUtilities() {
        List<String> list = Arrays.asList(animals);
        log("== print the resulting list");
        print(list);

        log("== print the shuffled list");
        Collections.shuffle(list);
        print(list);

        log("== print the sorted list (used natural ordering)");
        Collections.sort(list);
        print(list);

        log("== binary Search of Elephant after sorting: " + Collections.binarySearch(list, "Elephant"));

        log("== print the reversed list");
        Collections.reverse(list);
        print(list);

        log("== binary Search of Elephant without sorting: " + Collections.binarySearch(list, "Elephant"));

        log("== print the sorted by length list of word");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        print(list);

        log("== max (used natural ordering): " + Collections.max(list));
        log("== min (used natural ordering): " + Collections.min(list));

        log("== frequency of Cow: " + Collections.frequency(list, "Cow"));
        log("== frequency of Human: " + Collections.frequency(list, "Human"));

        log("== replace Cow to Pig: ");
        Collections.replaceAll(list, "Cow", "Pig");
        print(list);

        log("== swap: swap first and last values: ");
        Collections.swap(list, 0, list.size() - 1);
        print(list);

        log("== rotate: rotate by 2: ");
        Collections.rotate(list, 2);
        print(list);

        log("== indexOfSubList: look for sublist in the list ");
        List<String> subList = Arrays.asList(new String[]{list.get(5), list.get(6)});
        print(subList);
        log("sublist position: " + Collections.indexOfSubList(list, subList));

        log("== fill: fill list with the same values: ");
        Collections.fill(list, ".");
        print(list);
    }

}
