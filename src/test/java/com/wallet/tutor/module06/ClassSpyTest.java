package com.wallet.tutor.module06;

import static java.lang.System.out;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

enum ClassMember {CONSTRUCTOR, FIELD, METHOD, CLASS, ALL}

@Slf4j
public class ClassSpyTest {

    @Test
    public void testClassSpy() {
        ClassSpyTest.spy("com.wallet.market.jva008.module05.ExampleClass", ClassMember.ALL.toString());
    }

    public static void spy(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);
            out.format("Class: %n  %s%n%n", c.getCanonicalName());

            Package p = c.getPackage();
            out.format("Package: %n  %s%n%n", (p != null ? p.getName() : "-- No Package --"));

            for (int i = 1; i < args.length; i++) {
                switch (ClassMember.valueOf(args[i])) {
                    case CONSTRUCTOR:
                        printMembers(c.getConstructors(), "Constructor");
                        break;
                    case FIELD:
                        printMembers(c.getFields(), "Fields");
                        break;
                    case METHOD:
                        printMembers(c.getMethods(), "Methods");
                        break;
                    case CLASS:
                        printClasses(c);
                        break;
                    case ALL:
                        printMembers(c.getConstructors(), "Constuctors");
                        printMembers(c.getFields(), "Fields");
                        printMembers(c.getMethods(), "Methods");
                        printClasses(c);
                        break;
                    default:
                        assert false;
                }
            }

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printMembers(Member[] mbrs, String s) {
        out.format("%s:%n", s);

        for (Member mbr : mbrs) {
            if (mbr instanceof Field) {
                out.format("  %s%n", ((Field) mbr).toGenericString());
            } else if (mbr instanceof Constructor) {
                out.format("  %s%n", ((Constructor<?>) mbr).toGenericString());
            } else if (mbr instanceof Method) {
                out.format("  %s%n", ((Method) mbr).toGenericString());
            }
        }

        if (mbrs.length == 0) {
            out.format("  -- No %s --%n", s);
        }
        out.format("%n");
    }

    private static void printClasses(Class<?> c) {
        out.format("Classes: %n");
        Class<?>[] clss = c.getClasses();
        for (Class<?> cls : clss) {
            out.format("  %s%n", cls.getCanonicalName());
        }
        if (clss.length == 0) {
            out.format("  -- No member interfaces, classes, or enums --%n");
        }
        out.format("%n");
    }
}

@Slf4j
class ExampleClass {
    @Getter
    private String text;
    public String name;
    private int counter;

    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnotation {
        String name() default "";
    }

    public ExampleClass() {
    }

    public ExampleClass(String text, int counter) {
        super();
        this.text = text;
        this.counter = counter;
    }

    @MyAnnotation(name = "print me!")
    public void printIt() {
        log.info("printIt() no param");
    }

    public void printItString(String temp) {
        log.info("printIt() with param String: " + temp);
    }

    public void printItInt(int temp) {
        log.info("printIt() with param int: " + temp);
    }

    public void setCounter(int counter) {
        this.counter = counter;
        log.info("setCounter() set counter to: " + counter);
    }

    public void printCounter() {
        log.info("printCounter(): " + this.counter);
    }

}
