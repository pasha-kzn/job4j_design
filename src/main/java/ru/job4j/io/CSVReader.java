package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void main(String[] args) throws Exception {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    private static void validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        List.of("path", "delimiter", "out", "filter").forEach(v -> argsName.get(v));
        File file = new File(argsName.get("path"));
        if (!file.isFile()) {
            throw new IllegalArgumentException("Это не файл: %s".formatted(file.getAbsoluteFile()));
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Файл должен иметь расширение '.csv'");
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        List<String> filterFields = Arrays.asList(argsName.get("filter").split(","));
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path"))).useDelimiter(System.lineSeparator());
             PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            String headerLine = scanner.nextLine();
            if (headerLine == null) {
                throw new IllegalArgumentException("Файл пустой");
            }
            List<String> columns = Arrays.asList(headerLine.split(delimiter));
            List<Integer> indexes = getIndexes(columns, filterFields);
            output.print(getRow(headerLine, indexes, delimiter));
            output.println();
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                output.print(getRow(line, indexes, delimiter));
                output.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> getIndexes(List<String> columns, List<String> filterFields) {
        Map<String, Integer> columnIndexMap = new HashMap<>();
        for (int i = 0; i < columns.size(); i++) {
            columnIndexMap.put(columns.get(i), i);
        }
        List<Integer> indexes = new ArrayList<>();
        for (String field : filterFields) {
            Integer idx = columnIndexMap.get(field);
            if (idx != null) {
                indexes.add(idx);
            }
        }
        return indexes;
    }

    private static String getRow(String line, List<Integer> indexes, String delimiter) {
        List<String> columns = Arrays.asList(line.split(delimiter));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indexes.size(); i++) {
            sb.append(columns.get(indexes.get(i)));
            if (i < indexes.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
}