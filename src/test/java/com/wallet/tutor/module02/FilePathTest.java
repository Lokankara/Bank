package com.wallet.tutor.module02;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FilePathTest {

    @Test
    public void testPath() {
        File f = new File("test/.././file.txt");
        log.info("Name: " + f.getName());
        log.info("Path: " + f.getPath());
        log.info("Absolute Path: " + f.getAbsolutePath());

        try {
            log.info("Canonical Path: " + f.getCanonicalPath());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
