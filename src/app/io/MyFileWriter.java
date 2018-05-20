package app.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {

    public static void writeFile(String fileName, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            bw.write(content);

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
