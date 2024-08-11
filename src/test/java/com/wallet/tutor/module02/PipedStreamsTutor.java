package com.wallet.tutor.module02;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

@Slf4j
public class PipedStreamsTutor {

    public static void main(String[] args) {

        try (PipedOutputStream out = new PipedOutputStream();
             PipedInputStream in = new PipedInputStream(out)) {
            out.write('L');
            out.write('U');
            out.write('X');
            out.write('O');
            out.write('F');
            out.write('T');

            for (int i = 0; i < 6; i++) {
                log.info(String.valueOf((char) in.read()));
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
