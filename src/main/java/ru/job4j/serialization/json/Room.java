package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Room {

    @XmlAttribute
    private int length;

    @XmlAttribute
    private int width;

    public Room() {

    }

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
