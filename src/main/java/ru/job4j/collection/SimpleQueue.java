package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        } else if (size == 1) {
            size--;
            return input.pop();
        }
        int counter = size;
        while (counter != 0) {
            output.push(input.pop());
            counter--;
        }
        T value = output.pop();
        while (counter < size - 1) {
            input.push(output.pop());
            counter++;
        }
        size--;
        return value;
    }

    public void push(T value) {
        input.push(value);
        size++;
    }
}