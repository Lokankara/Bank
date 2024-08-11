package com.wallet.tutor.module02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class PrintWriterTest {

    private static final String FILE_OUT = "files/std.out";
    PrintStream out;

    public void redirectOut() {
        try {
            out = new PrintStream(FILE_OUT);
            System.setOut(out);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    public void printlnData() {
        log.info("output test");
    }

    @Test
    public void testOutToFile() {
        redirectOut();
        printlnData();

        BufferedReader stdOut;
        try {
            stdOut = new BufferedReader(new FileReader(FILE_OUT));
            String line = stdOut.readLine();
            assertEquals("output test", line);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
