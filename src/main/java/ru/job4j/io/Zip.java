package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.lang.String.format;

public class Zip {
    private static ArgsName argsName;

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        argsName = ArgsName.of(args);
        validate(args);
        List<Path> sources = Search
                .search(Paths.get(argsName.get("d")), path -> !path.toFile().getName().endsWith(argsName.get("e")));
        new Zip().packFiles(sources, new File(argsName.get("o")));
    }

    private static void validate(String[] args) {
        List.of("d", "e", "o").forEach(argsName::get);
        File file = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(format("Это не директория: %s", file.getAbsoluteFile()));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Файл должен иметь расширение '.zip'");
        }
    }
}