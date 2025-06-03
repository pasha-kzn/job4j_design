package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException(format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] strings = arg.split("=", 2);
            values.put(strings[0].substring(1), strings[1]);
        }
    }

    public static ArgsName of(String[] args) {
        validate(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain an equal sign", arg));
            }
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not start with a '-' character", arg));
            }
            if (arg.startsWith("-=")) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain a key", arg));
            }
            String[] strings = arg.split("=", 2);
            if (strings[1].isEmpty()) {
                throw new IllegalArgumentException(format("Error: This argument '%s' does not contain a value", arg));
            }
        }
    }
}