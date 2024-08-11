package com.wallet.tutor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logger {

    public static void log(Object s) {
        log.info(s.toString());
    }
    public static void log(String s) {
        log.info(s);
    }
}
