package ru.job4j.io;

import java.io.File;

import static java.lang.String.format;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.println(format("Размер директории: %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.printf("Имя файла - %s, размер файла - %s байт%n", subfile.getName(), subfile.length());
        }
    }
}
