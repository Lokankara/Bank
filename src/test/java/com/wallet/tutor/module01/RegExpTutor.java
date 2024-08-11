package com.wallet.tutor.module01;

import static com.wallet.tutor.Logger.log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.wallet.tutor.Logger;
import org.junit.jupiter.api.Test;

public class RegExpTutor {
    private String charRegEx = "a-zA-Z";

    public Email getEmail(String emailString) {

        Email email = new Email();

        String regEx = String.format("%s0-9_!#$%%&'*+/=?`{|}~.-^", charRegEx);

        String regexPattern = String.format("^([%s]{1,64})@([%s]{1,64})(\\.)([%s]{2,4})$", regEx, regEx, charRegEx);

        Matcher matcher = match(regexPattern, emailString);

        if (matcher.find()) {
            email.name = matcher.group(1);
            email.domainName = matcher.group(2);
            email.domainZone = matcher.group(4);
        }
        return email;
    }

    /**
     * Returns a list of strings according to the text representation of strings:
     * Takes a string
     * "List of animals: Cow, Goose, Cat, Dog, Elephant, Hare, Snake, Hen, Horse, Man."
     * And returns an array of individual animals
     */
    public String[] getAnimalsArray(String animalsString) {

        ArrayCopyTutor copy = new ArrayCopyTutor();

        String regexPattern = String.format(" ([%s]{1,64})([\\.,])", charRegEx);

        Matcher matcher = match(regexPattern, animalsString);

        while (matcher.find()) {
            String found = matcher.group(1);
            copy.addAnimal(found);
            Logger.log(found);
        }

        return copy.animals;
    }

    public static Matcher match(String pattern, String word) {

        Pattern compile = Pattern.compile(pattern);

        return compile.matcher(word);
    }

    @Test
    public void testGetEmail() {
        Email email = getEmail("ivanov@mail.ru");
        assertEquals("ivanov", email.name);
        assertEquals("mail", email.domainName);
        assertEquals("ru", email.domainZone);

    }

    @Test
    public void testGetAnimalsArray() {
        String[] animals = {"Cow", "Goose", "Cat", "Dog", "Elephant", "Hare", "Snake", "Chicken", "Horse", "Man"};
        String animalsString = "List of animals: Cow, Goose, Cat, Dog, Elephant, Hare, Snake, Chicken, Horse, Man.";
        assertArrayEquals(animals, getAnimalsArray(animalsString));
    }

    class Email {
        String name;
        String domainName;
        String domainZone;
    }

}
