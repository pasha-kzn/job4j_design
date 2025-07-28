package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUsage {
    public static void main(String[] args) {
        final House house = new House(2020, "brick", true,
                new Room[]{new Room(4, 3), new Room(8, 4)});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(house));

        final String homeJson =
                "{"
                        + "\"constructionsYear\":1968,"
                        + "\"material\":\"sticks\","
                        + "\"hasInternet\":false,"
                        + "\"rooms\":"
                        + "["
                        + "{"
                        + "\"length\":2,"
                        + "\"width\":3"
                        + "},"
                        + "{"
                        + "\"length\":2,"
                        + "\"width\":3"
                        + "}"
                        + "]"
                        + "}";
        final House houseMod = gson.fromJson(homeJson, House.class);
        System.out.println(houseMod);
    }
}
