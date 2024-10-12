package com.wallet.tutor.module08;

import com.wallet.tutor.module08.defaultInterfaces.Function;
import com.wallet.tutor.module08.defaultInterfaces.Supplier;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class LambdaTest {

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(SOURCE sourceCollection, Supplier<DEST> collectionFactory) {
        DEST result = collectionFactory.get();
        result.addAll(sourceCollection);
        return result;
    }

    private static String value = "hi";

    public String process(String s, Function<String, String> f) {
        return f.apply(s);
    }

    public String process2(LambdaTest t, Function<LambdaTest, String> f) {
        return f.apply(t);
    }

    public String addExclamation(String s) {
        return String.format("%s!", s);
    }

    public String addExclamation2() {
        return String.format("%s!", value);
    }

    public static String addQuestion(String s) {
        return String.format("%s?", s);
    }

    public static void main(String[] args) {

        LambdaTest lambdaTest = new LambdaTest();
        String s = lambdaTest.process("hello", LambdaTest::addQuestion);
        log.info(lambdaTest.process2(lambdaTest, LambdaTest::addExclamation2));
        log.info(s);
        log.info(lambdaTest.process("hello", x -> x + "?"));
    }
}
