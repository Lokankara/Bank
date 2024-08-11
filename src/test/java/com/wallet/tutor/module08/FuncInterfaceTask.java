package com.wallet.tutor.module08;

import static java.lang.System.out;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Implement interface Summator and Printer by using:
 * - lambda expression
 * - static method reference
 * - non-static method reference
 */
public class FuncInterfaceTask {

    interface Summator {
        int getSum(int a, int b);
    }

    interface Printer {
        void printSum(int a);
    }

    interface Info {
        String get();

        String info();
    }

    static class MyInfo implements Info {
        String name;

        public MyInfo() {
        }

        public MyInfo(String name) {
            this.name = name;
        }

        public String get() {
            return "Hello, I'm MyInfo.get()";
        }

        public String info() {
            return "Hello, I'm MyInfo.info()";
        }
    }

    public static void main(String[] args) {
        Summator f = Integer::sum;
        assertEquals(f.getSum(1, 2), 3);

        Printer p = out::println;
        p.printSum(f.getSum(5, 5));

        Info info = makeInfo(MyInfo::new);

        out.println(info.info());
        out.println(info.get());

        Info[] infos = {new MyInfo("first"), new MyInfo("second")};

        printAll(infos, Info::get);
        printAll(infos, Info::info);
    }

    private static Info makeInfo(Supplier<Info> s) {
        return s.get();
    }

    private static void printAll(Info[] infos, Function<Info, String> string) {
        Arrays.stream(infos).forEach(i -> out.println(string.apply(i)));
    }
}
