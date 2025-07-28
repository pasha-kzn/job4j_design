package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Arrays;

@XmlRootElement(name = "home")
@XmlAccessorType(XmlAccessType.FIELD)
public class House {

    @XmlAttribute
    private int constructionsYear;

    @XmlAttribute
    private String material;

    @XmlAttribute
    private boolean hasInternet;

    private Room[] rooms;

    public House() {

    }

    public House(int constructionsYear, String material, boolean hasInternet, Room[] rooms) {
        this.constructionsYear = constructionsYear;
        this.material = material;
        this.hasInternet = hasInternet;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Home{"
                + "constructionsYear = " + constructionsYear
                + ", material = " + material
                + ", hasInternet = " + hasInternet
                + ", rooms = " + Arrays.toString(rooms)
                + '}';
    }
}
