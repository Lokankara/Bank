package com.wallet.tutor.module02;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileStreamTutor {
    private static final String FILES_TEST_PATH = "files/test.txt";
    private static final String TEST_LINE = "test line";

    /**
     * Writes a string TEST_LINE to the file FILES_TEST_PATH ,
     * converting the string into array of bytes.
     * For the writing, use the class FileOutputStream.
     */
    public void writeToFile() {

        try (FileOutputStream outputStream =
                     new FileOutputStream((FILES_TEST_PATH))) {

            outputStream.write(TEST_LINE.length());

            outputStream.write(TEST_LINE.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a line from a file and returns it using FileInputStream.
     *
     * @return bytes
     */
    public String readFromFile() {

        try (FileInputStream inputStream =
                     new FileInputStream(FILES_TEST_PATH)) {

            int index = inputStream.read();

            byte[] bytes = new byte[index];

            inputStream.read(bytes, 0, index);

            return new String(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFileStream() {
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
