package com.wallet.tutor.module08;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class FuncInterfaceTutor {

    interface Func {
        void test();
    }

    interface Func2 {
        int exec(String a);
    }

    public static void testImplStatic() {
        log.info("I'm a static test method inside FuncInterfaceTutor");
    }

    public void testImpl() {
        log.info("I'm not static test method inside FuncInterfaceTutor");
    }

    public int getLength(String s) {
        return s.length();
    }

    public void printLength(String s, Func2 f) { // String::length
        log.info(String.valueOf(f.exec(s)));
    }

    interface Info {
        String get();

        String random();

        Info info();
    }

    static class MyInfo implements Info {
        @Override
        public String get() {
            return "I'm MyInfo object, created and alive!";
        }

        @Override
        public String random() {
            return "hello " + Math.random();
        }

        @Override
        public Info info() {
            Function<Info, String> get = Info::get;
            return null;
        }
    }

    public static String getInfo(Info info, Function<Info, String> s) {
        return "This is information from info: " + s.apply(info);
    }

    public static void printAll(Info[] infos, Function<Info, String> s) {
        Arrays.stream(infos).forEach(i -> log.info(s.apply(i)));
    }

    public Info createInfo() {
        return createInfo(MyInfo::new);
    }

    public Info createInfo(Supplier<Info> s) {
        Info info = s.get();
        // do some operations on info
        log.info(info.get());

        return info;
    }
    public static void main(String[] args) {
        Func f = () -> {
            log.info("Hi");
        };
        f.test();

        Func2 f2 = (a) -> {
            log.info("Hello");
            return Integer.parseInt(a);
        };
        log.info(String.valueOf(f2.exec("1")));

        // use static method reference
        Func f3 = FuncInterfaceTutor::testImplStatic;
        f3.test();

        // use non-static method reference
        FuncInterfaceTutor tutor = new FuncInterfaceTutor();
        tutor.testImpl();
        Func f4 = tutor::testImpl;
        f4.test();

        // use arbitary object method reference
        tutor.printLength("Hello", (s) -> s.length());
        tutor.printLength("Hi", String::length);

        // use constructor method reference
        tutor.createInfo(MyInfo::new);
        tutor.createInfo(() -> new MyInfo());

        // use arbitary object method reference for info object
        log.info(getInfo(new MyInfo(), Info::get));
        log.info(getInfo(new MyInfo(), Info::random));
        Info[] infos = {new MyInfo(), new MyInfo()};
        printAll(infos, Info::get);
    }
}
