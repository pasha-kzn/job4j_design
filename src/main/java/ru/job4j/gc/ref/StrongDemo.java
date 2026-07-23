package ru.job4j.gc.ref;

import java.util.concurrent.TimeUnit;

public class StrongDemo {

    private static void example1() {
        String string1 = "Hello";
        String string2 = "Hello";
        System.out.println(string1 == string2);
        //true
    }

    private static void example2() {
        String string1 = new String("Hello");
        String string2 = new String("Hello");
        String string3 = "Hello";
        String string4 = "Hello";
        System.out.println(string1 == string2);
        System.out.println(string3 == string4);
        System.out.println(string1 == string3);
        System.out.println(string2 == string4);
        //false-true-false-false
    }

    private static void example3() {
        String string1 = "Hello, world";
        String string2 = "Hello, " + "world";
        System.out.println(string1 == string2);
        //true - string2 выполняется почему то на этапе компиляции
    }

    private static void example4() {
        String string1 = "Hello, world";
        String string2 = "Hello, ";
        String string3 = string2 + "world";
        System.out.println(string1 == string3);
        //false - string3 вычисляется в процессе выполнения программы, а не на этапе компиляции
    }

    private static void example5() {
        String string1 = "Hello";
        String string2 = new String("Hello");
        String string3 = string2.intern();
        System.out.println(string1 == string3);
        System.out.println();
        //true - intern() помогает сослаться на тот же объект
    }

    private static void example6() {
        System.out.println(new String("New string") == new String("New string"));
        System.out.println(new String("Interned string").intern() == new String("Interned string").intern());
        //false - true
    }

    private static void example7() {
        Integer pool1 = 127;
        Integer pool2 = 127;
        System.out.println(pool1 == pool2);
        Integer heap1 = -129;
        Integer heap2 = -129;
        System.out.println(heap1 == heap2);
        System.out.println();
        //true - false
    }

    private static void example8() {
        Integer pool1 = new Integer(127);
        Integer pool2 = new Integer(127);
        System.out.println(pool1 == pool2);
        //false
    }

    public static void main(String[] args) throws InterruptedException {
//        example1();
//        example2();
//        example3();
//        example4();
//        example5();
//        example6();
        example7();
        example8();
//        example31();
//        example32();
//        example33();
    }

    private static void example31() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    private static void example32() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object!");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    private static void example33() {
        Integer pool1 = new Integer(127);
        Integer pool2 = new Integer(127);
        System.out.println(pool1 == pool2);
    }
}