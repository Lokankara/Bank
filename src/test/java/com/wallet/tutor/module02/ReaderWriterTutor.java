package com.wallet.tutor.module02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReaderWriterTutor {
    private static final String FILES_TEST_PATH = "files/test.txt";
    private static final String TEST_LINE = "test line";

    /**
     * Write line TEST_LINE to the file FILES_TEST_PATH, using
     * method write of class BufferedWriter.
     */
    public void writeToFile() {
        try (FileWriter fileWriter =
                     new FileWriter(FILES_TEST_PATH);

             BufferedWriter bufferedWriter =
                     new BufferedWriter(fileWriter)) {

            bufferedWriter.write(TEST_LINE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Reads line from file by using method readLine()
     * of class BufferedReader and returns it
     *
     * @return
     */
    public String readFromFile() {
        try (FileReader fileReader =
                     new FileReader(FILES_TEST_PATH);

             BufferedReader bufferedReader =
                     new BufferedReader(fileReader)) {

            return bufferedReader.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testStream() {
        writeToFile();
        String s = readFromFile();
        assertEquals(TEST_LINE, s);
    }

    @BeforeEach
    public void createFile() {
        File f = new File(FILES_TEST_PATH);
        try {
            f.delete();
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
