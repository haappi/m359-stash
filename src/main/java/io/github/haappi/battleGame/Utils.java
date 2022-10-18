package io.github.haappi.battleGame;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getSaveInformation(String jsonInformation, Long lastModified, Gson gson) {
        String noQuotes = jsonInformation.replaceAll("^\"|\"$", "");
        Map<?, ?> map = gson.fromJson(jsonInformation.replaceAll("^\"|\"$", ""), Map.class);
        return "Name: " + map.get("name") + "\n" +
                "Description: " + map.get("description") + "\n" +
                "Last Modified: " + SaveInstance.getLastModifiedString(lastModified) + "\n";
    }

    public static Map<?, ?> getMapFromString(String jsonInformation, Long lastModified, Gson gson) {
        Map<String, String> map = gson.fromJson(jsonInformation, Map.class);
        map.put("last_modified", String.valueOf(lastModified));
        return map;
    }

    public static <E> String listToString(List<E> list) {
        StringBuilder sb = new StringBuilder();
        for (E e : list) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    public static List<String> playerStatsToList(String stats) {
        ArrayList<String> newStats = new ArrayList<>(Arrays.asList(stats.split("\n")));
        newStats.remove(0); // PLAYER: \n
        newStats.remove(0); // \n
        for (String stat : newStats) {
            if (stat.toLowerCase().startsWith("fatigue")) {
                newStats.set(newStats.indexOf(stat), "Fatigue: " + doubleAsPercent(Double.parseDouble(stat.split(": ")[1])));
            }
        }
        return newStats;
    }

    public static String doubleAsPercent(double value) {
        return "" + value * 100 + "%";
    }
}
