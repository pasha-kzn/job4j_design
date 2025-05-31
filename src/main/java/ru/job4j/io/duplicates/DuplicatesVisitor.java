package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<String>> catalog = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty property = new FileProperty(attributes.size(), file.getFileName().toString());
        catalog.computeIfAbsent(property, v -> new ArrayList<>()).add(file.toAbsolutePath().toString());
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        catalog.values().stream()
                .filter(list -> list.size() > 1)
                .flatMap(List::stream)
                .forEach(System.out::println);
    }
}