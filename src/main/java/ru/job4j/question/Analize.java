package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> previousMap = new HashMap<>();
        Map<Integer, String> currentMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }
        int added = currentMap.keySet().stream()
                .filter(k -> !previousMap.containsKey(k))
                .toList().size();
        int changed = currentMap.entrySet().stream()
                .filter(e -> previousMap.containsKey(e.getKey()))
                .filter(e -> !previousMap.containsValue(e.getValue()))
                .toList().size();
        int deleted = previousMap.keySet().stream()
                .filter(k -> !currentMap.containsKey(k))
                .toList().size();
        return new Info(added, changed, deleted);
    }
}