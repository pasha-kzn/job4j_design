package ru.job4j.serialization.json;

public class Room {
    private final int length;
    private final int width;

    public Room(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Room{"
                + "length = " + length
                + ",width = " + width
                + '}';
    }
}
