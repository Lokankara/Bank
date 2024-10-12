package com.wallet.tutor.module02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class FileTest {
    private static final String PATH = "test";
    private static final String FILE_NAME = "test.txt";

    /**
     * The method must create a folder "test" and a file "test.txt" inside it
     * Also, output in the full path to the log file you have created
     */
    public void createFile() {
        File[] fileMap = mapper();
        boolean folder = fileMap[0].mkdir();
        log.info(String.format("New Dir: %s", folder));
        try {
            boolean newFile = fileMap[1].createNewFile();
            log.info(String.format("New File: %s", newFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method should remove the "test" folder and the "test/test.txt" file
     */
    public void deleteFile() {

        File[] fileMap = mapper();
        boolean deleteFile = fileMap[1].delete();
        boolean deleteDir = fileMap[0].delete();
        log.info(String.format("File deleted: %s", deleteFile));
        log.info(String.format("Dir deleted: %s", deleteDir));
    }

    private File[] mapper() {
        File dir = new File(PATH);
        File file = new File(PATH + "/" + FILE_NAME);
        File[] array = {dir, file};
        log.info(file.getAbsolutePath());
        log.info(dir.getAbsolutePath());
        return array;
    }

    @Test
    public void testCreateFile() {
        createFile();
        File f = new File("test/test.txt");
        assertTrue(f.exists());
    }

    @Test
    public void testDeleteFile() {
        deleteFile();
        File f = new File("test/test.txt");
        assertFalse(f.exists());
        assertFalse(new File("test").exists());
    }
}
