package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte by = 127;
        short sh = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;
        float f = 100.05f;
        double d = 100500.06;
        boolean b = true;
        char c = 'c';
        LOG.debug("Примеры примитивных типов Java:byte - {},short - {},int - {},long - {},float - {}, double - {},boolean - {},char - {},",
                by, sh, i, l, f, d, b, c);
    }
}