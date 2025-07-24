package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUsage {
    public static void main(String[] args) {
        final Home home = new Home(2020, "brick", true,
                new Room[]{new Room(4, 3), new Room(8, 4)});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(home));

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
        final Home homeMod = gson.fromJson(homeJson, Home.class);
        System.out.println(homeMod);
    }
}
