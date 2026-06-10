package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        /*
        легаси подход
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(format("Это не директория: %s", file.getAbsoluteFile()));
        }
         */
        Path path = Path.of(cachingDir);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Путь не существует: " + path);
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Это не директория: " + path);
        }
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("Нет прав на чтение: " + path);
        }
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String data;
        Path path = Path.of(cachingDir).resolve(key);
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException(
                    String.format("Нет прав на чтение файла: %s", path));
        }
        try {
            data = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + path, e);
        }
        return data;
    }

    protected String alternativeLoad(String key) {
        Path path = Path.of(cachingDir).resolve(key);
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException(
                    String.format("Нет прав на чтение файла: %s", path));
        }
        StringBuilder content = new StringBuilder();
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> content.append(line).append("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + path, e);
        }
        return content.toString();
    }
}