package com.wallet.tutor.module01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringBuilderTutor {

    String[] animals = {"Cow", "Goose", "Cat", "Dog", "Elephant", "Hare", "Snake", "Chicken", "Horse", "Man"};

    /**
     * The method should return a string:
     * "List of animals: Cow, Goose, ..., Man."
     * For implementation, use StringBuilder
     */
    public String getAnimalsString() {
        StringBuilder builder = new StringBuilder();
        for (String animal : animals) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(animal);
        }
        builder.append(".");
        builder.insert(0, "List of animals: ");
        return builder.toString();
    }

    @Test
    public void testGetAnimalsString() {
        String animalsString = getAnimalsString();
        assertEquals("List of animals: Cow, Goose, Cat, Dog, Elephant, Hare, Snake, Chicken, Horse, Man.", animalsString);
    }

}
