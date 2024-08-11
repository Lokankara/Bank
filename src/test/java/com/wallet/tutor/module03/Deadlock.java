package com.wallet.tutor.module03;

public class Deadlock implements Runnable {
    public static void main(String[] args) {
        Object a = "Resource A";
        Object b = "Resource B";
        Thread t1 = new Thread(new Deadlock(a, b));
        Thread t2 = new Thread(new Deadlock(b, a));
        t1.start();
        t2.start();
    }

    private Object firstResource;
    private Object secondResource;

    public Deadlock(Object first, Object second) {
        firstResource = first;
        secondResource = second;
    }

    public void run() {
        while (true) {
            synchronized (firstResource) {
                synchronized (secondResource) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }
}