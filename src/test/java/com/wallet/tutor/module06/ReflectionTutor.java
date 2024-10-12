package com.wallet.tutor.module06;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class ReflectionTutor {
    static final String introspectClass = "com.wallet.market.tutor.module06.ExampleClass";

    @Test
    public void testReflection() {
        String[] array = {"printIt", "printItString", "printItInt", "setCounter", "printCounter"};

        try {
            Method print;

            Class<?> clazz = Class.forName(introspectClass);

            Object instance = clazz.getDeclaredConstructor().newInstance();

            Method[] methods = clazz.getMethods();

            Field[] fields = clazz.getDeclaredFields();

            Arrays.stream(fields).map(Field::getName).forEach(log::info);

            Arrays.stream(methods)
                    .filter(method -> method.getDeclaringClass() == clazz)
                    .map(method -> method.getName() + ": " + method.toGenericString())
                    .forEach(log::info);

            print = clazz.getDeclaredMethod(array[0]);
            print.invoke(instance, (Object[]) null);

            print = clazz.getDeclaredMethod(array[1], String.class);
            print.invoke(instance, "");

            print = clazz.getDeclaredMethod(array[2], Integer.TYPE);
            print.invoke(instance, 2);

            print = clazz.getDeclaredMethod(array[3], Integer.TYPE);
            print.invoke(instance, 3);

            print = clazz.getDeclaredMethod(array[4]);
            print.invoke(instance, (Object[]) null);

            // TODO: show all constructors (use method showConstructors())

            // TODO: list all methods, return types and arguments

            // TODO: list all fields and types of the class


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showConstructors(Class<String> clazz) {
        for (Constructor<?> constr : clazz.getConstructors()) {
            StringBuilder sb = new StringBuilder();
            for (Class<?> param : constr.getParameterTypes()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(param.getSimpleName());
            }
            sb.insert(0, "constructor: " + constr.getName() + "(");
            sb.append(")");
            log.info(sb.toString());
        }
        log.info("SuperClass: " + clazz.getSuperclass().getSimpleName());
    }

    @Test
    public void testShowConstructors() {
        showConstructors(java.lang.String.class);
    }

}

