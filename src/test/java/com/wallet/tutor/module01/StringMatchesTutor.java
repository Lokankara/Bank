package com.wallet.tutor.module01;

import static com.wallet.tutor.module01.RegExpTest.match;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StringMatchesTutor {

    /**
     * Checks the correctness of the email address
     */
    public boolean checkIsEmail(String email) {

        String regEx = "a-zA-Z0-9_!#$%&'*+/=?`{|}~.-^";

        String regexPattern = String.format("^([%s]{1,64})@([%s]{1,64})(\\.)([a-zA-Z]{2,4})$", regEx, regEx);

        return match(regexPattern, email).find();
    }

    /**
     * Make sure greeting greetings look like
     * Hello, Ivan Ivanov!
     * or
     * Hello, Peter Pan!
     * I.e. Begins on Hello, ends on!
     * And contains 2 words for the name and surname,
     * And the name and surname are not shorter than 3 letters
     * And start with a capital letter
     */
    public boolean checkGreeting(String greeting) {

        String regexPattern = "(^Hello, ?)((\\p{Upper}.{2,64}) (\\p{Upper}.{2,64}) ?)(!$)";

        return match(regexPattern, greeting).find();
    }


    @Test
    public void testCheckGreeting() {
        assertTrue(checkGreeting("Hello, Ivan Ivanov!"));
        assertTrue(checkGreeting("Hello,Peter Pan!"));
        assertTrue(checkGreeting("Hello, Peter Pan!"));
        assertTrue(checkGreeting("Hello, Peter Pan !"));

        assertFalse(checkGreeting("Hi, Peter Pan!"));
        assertFalse(checkGreeting("Hello, Peter Pan"));
        assertFalse(checkGreeting("Hello, Li Song!"));
        assertFalse(checkGreeting("Hello, Kui Le!"));
        assertFalse(checkGreeting("Hello, Peter!"));
        assertFalse(checkGreeting("Hello, peter Pan!"));
        assertFalse(checkGreeting("Hi, Peter is the first!"));
    }

    @Test
    public void testCheckIsEmail() {
        assertTrue(checkIsEmail("ivanov@mail.ru"));
        assertTrue(checkIsEmail("ivanov@mail.com.uk"));
        assertFalse(checkIsEmail("ivan ivanov@mail.com.uk"));
        assertFalse(checkIsEmail("ivanov@mailcom"));
    }
}
