package com.wallet.tutor.module07;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class AnnotationTutor {
    static final String introspectClass = "com.wallet.market.tutor.module07.ExampleClass";

    @Test
    public void testAnnotations() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> cls = Class.forName(introspectClass);

        Method method = cls.getDeclaredMethod("printIt", new Class[0]);

        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);

        boolean myAnnotation = method.isAnnotationPresent(MyAnnotation.class);

        if (myAnnotation) {
            log.info(String.format("my annotation name = %s", annotation));
        }

        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations.length > 0) {
                for (Annotation annotation1 : annotations) {
                    if (annotation1 instanceof Default) {
                        log.info(String.format("Field: %s, annotation name = %s, annotation value = %s%n",
                                field.getName(),
                                ((Default) annotation1).name(),
                                ((Default) annotation1).value()));
                    }
                }

            }
        }
    }

    @Test
    public void testDefaultField() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
        ExampleClass ex = new ExampleClass();
        String name = getFieldValue(ex, "name");
        assertEquals(name, "noname");
        ex.name = "my name";
        String name2 = getFieldValue(ex, "name");
        assertEquals(name2, "my name");
    }

    public String getFieldValue(Object obj, String fieldName) throws ClassNotFoundException, IllegalAccessException {

        Class<? extends Object> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {

            String name = field.getName();

            field.setAccessible(true);

            if (field.get(obj) != null) {
                return field.get(obj).toString();
            }

            if (name.equals(fieldName)) {

                Annotation[] annotations = field.getDeclaredAnnotations();
                if (annotations.length > 0) {
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof Default) {
                            Default myAnnotation = (Default) annotations[0];
                            return myAnnotation.value();
                        }
                    }
                }
            }
        }
        return null;
    }

}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String name() default "";
}


@Retention(RetentionPolicy.RUNTIME)
@interface Default {
    String name() default "my text";

    String value() default "noname";
} 
