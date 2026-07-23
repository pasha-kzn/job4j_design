package ru.job4j.kiss.fool;

import java.util.Scanner;
import java.util.function.BiPredicate;

public class Fool {
    private static BiPredicate<Integer, Integer> cond = (v, d) -> v % d == 0;

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
//            if (startAt % 3 == 0 && startAt % 5 == 0) {
//                System.out.println("FizzBuzz");
//            } else if (startAt % 3 == 0) {
//                System.out.println("Fizz");
//            } else if (startAt % 5 == 0) {
//                System.out.println("Buzz");
//            } else {
//                System.out.println(startAt);
//            }
            System.out.println(getRightAnswer(startAt));
            startAt++;
            var answer = input.nextLine();
//            if (startAt % 3 == 0 && startAt % 5 == 0) {
//                if (!"FizzBuzz".equals(answer)) {
//                    System.out.println("Ошибка. Начинай снова.");
//                    startAt = 0;
//                }
//            } else if (startAt % 3 == 0) {
//                if (!"Fizz".equals(answer)) {
//                    System.out.println("Ошибка. Начинай снова.");
//                    startAt = 0;
//                }
//            } else if (startAt % 5 == 0) {
//                if (!"Buzz".equals(answer)) {
//                    System.out.println("Ошибка. Начинай снова.");
//                    startAt = 0;
//                }
//            } else {
//                if (!String.valueOf(startAt).equals(answer)) {
//                    System.out.println("Ошибка. Начинай снова.");
//                    startAt = 0;
//                }
//            }
            if (!answer.equals(getRightAnswer(startAt))) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }

    private static String getRightAnswer(int value) {
        if (cond.test(value, 3) && cond.test(value, 5)) {
            return "FizzBuzz";
        }
        if (cond.test(value, 5)) {
            return "Buzz";
        }
        if (cond.test(value, 3)) {
            return "Fizz";
        }
        return String.valueOf(value);
    }
}
