package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            if (Integer.parseInt(line) % 2 == 0) {
                System.out.println(line + " - четное число");
            } else {
                System.out.println(line + " - нечетное число");
            }
        }
    }
}