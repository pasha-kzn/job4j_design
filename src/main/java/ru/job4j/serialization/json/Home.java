package ru.job4j.serialization.json;

import java.util.Arrays;

public class Home {
    private final int constructionsYear;
    private final String material;
    private final boolean hasInternet;
    private final Room[] rooms;

    public Home(int constructionsYear, String material, boolean hasInternet, Room[] rooms) {
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
