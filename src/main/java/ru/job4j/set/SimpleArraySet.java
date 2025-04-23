package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);
    private int size;

    @Override
    public boolean add(T value) {
        boolean contains = contains(value);
        if (!contains) {
            set.add(value);
            size++;
        }
        return !contains;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        for (T t : set) {
            contains = Objects.equals(value, t);
            if (contains) {
                break;
            }
        }
        return contains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}