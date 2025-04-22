package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;
    private Node<T> tail;

    public void add(T value) {
        Node<T> nextNode = new Node<>(value, null);
        if (head == null) {
            head = nextNode;
            tail = head;
        } else {
            tail.next = nextNode;
            tail = tail.next;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = head;
        int counter = 0;
        while (counter != index) {
            currentNode = currentNode.next;
            counter++;
        }
        return currentNode.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = head;
        head = head.next;
        T value = node.item;
        node.item = null;
        node.next = null;
        size--;
        modCount++;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int index = 0;
            private Node<T> node;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (node == null) {
                    node = head;
                } else {
                    node = node.next;
                }
                index++;
                return node.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}