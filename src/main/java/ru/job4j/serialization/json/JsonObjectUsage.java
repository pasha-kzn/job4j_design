package ru.job4j.serialization.json;

import org.json.JSONObject;

public class JsonObjectUsage {
    public static void main(String[] args) {
        final House house = new House(2020, "brick", true,
                new Room[]{new Room(4, 3), new Room(8, 4)});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("constructionsYear", house.getConstructionsYear());
        jsonObject.put("material", house.getMaterial());
        jsonObject.put("hasInternet", house.isHasInternet());
        jsonObject.put("rooms", house.getRooms());
        System.out.println(new JSONObject(house).toString());
    }
}
