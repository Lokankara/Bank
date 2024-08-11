package com.wallet.tutor.module03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PipedReaderWriterTutor {
    private static final String TEST_LINE = "test line";
    private PipedWriter pipedWriter = new PipedWriter();
    private PipedReader pipedReader;
    private String pipeReaderResult = null;
    private Object waiter = new Object();

    class PipeWriterThread implements Runnable {
        public void run() {
            BufferedWriter out = new BufferedWriter(pipedWriter);
            try {
                out.write(TEST_LINE);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class PipeReaderThread implements Runnable {
        public void run() {
            BufferedReader in = new BufferedReader(pipedReader);
            try {
                pipeReaderResult = in.readLine();
                synchronized (waiter) {
                    waiter.notify();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testPipe() throws InterruptedException {
        try {
            pipedReader = new PipedReader(pipedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new PipeWriterThread()).start();
        new Thread(new PipeReaderThread()).start();

        assertEquals(TEST_LINE, pipeReaderResult);
    }

}
