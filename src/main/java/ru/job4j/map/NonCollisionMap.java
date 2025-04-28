package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        boolean isEmpty = table[index] == null;
        if (isEmpty) {
            table[index] = new MapEntry<>(key, value);
        }
        count++;
        modCount++;
        return isEmpty;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        boolean isEmpty = table[index] == null;
        V value = null;
        if (!isEmpty && areEqual(key, table[index].key)) {
            value = table[index].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        boolean isEmpty = table[index] == null;
        boolean result = false;
        if (!isEmpty && areEqual(key, table[index].key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    private boolean areEqual(K expectedKey, K actualKey) {
        boolean result;
        if (expectedKey == null || actualKey == null) {
            result =  expectedKey == actualKey;
        } else {
            result = expectedKey.hashCode() == actualKey.hashCode() && Objects.equals(expectedKey, actualKey);
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & capacity - 1;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                int index = getIndex(mapEntry.key);
                tempTable[index] = mapEntry;
            }
        }
        table = tempTable;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap map = new NonCollisionMap();
        System.out.println(map.hash(0) == 0);
        System.out.println(map.hash(65535) == 65535);
        System.out.println(map.hash(65536) == 65537);
        System.out.println(map.indexFor(0) == 0);
        System.out.println(map.indexFor(7) == 7);
        System.out.println(map.indexFor(8) == 0);
        System.out.println(map.indexFor(31) == 7);
        System.out.println(map.indexFor(32) == 0);
    }
}