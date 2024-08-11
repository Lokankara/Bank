package com.wallet.tutor.module07;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ExampleClass {
    @Default("my text")
    private String text;
    @Default("noname")
    public String name;
    private int counter;

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
        log.info("printIt() with param String : " + temp);
    }

    public void printItInt(int temp) {
        log.info("printIt() with param int : " + temp);
    }

    public void setCounter(int counter) {
        this.counter = counter;
        log.info("setCounter() set counter to : " + counter);
    }

    public void printCounter() {
        log.info("printCounter() : " + this.counter);
    }

}