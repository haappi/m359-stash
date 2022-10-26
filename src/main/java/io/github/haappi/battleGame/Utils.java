package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Opponent;
import io.github.haappi.battleGame.Classes.Player;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
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

    public static double getRandomDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    public static double randomlySomething(double stat) {
        return getRandomBoolean() ? stat * getRandomDouble(0.2, 0.4) : stat * getRandomDouble(0.2, 0.4) * -1;
    }

    public static double reduceByFatigue(double stat, double fatigueLevel) {
        return stat - (stat * fatigueLevel);
    }

    public static void increaseFatigue(Player player) {
        if (getRandomBoolean()) {
            player.setFatigueLevel(player.getFatigueLevel() + getRandomDouble(0.01, 0.2));
        }
    }

    public static void increaseFatigue(Opponent opponent) {
        if (getRandomBoolean()) {
            opponent.setFatigueLevel(opponent.getFatigueLevel() + getRandomDouble(0.01, 0.2));
        }
    }

//    public static double weakenStuff(Player player, Opponent opponent, String type) {
//        switch (type.toLowerCase()) {
//            case "attack":
//                return reduceByFatigue(opponent.getAttack(), player.getFatigueLevel());
//            case "defense":
//                return reduceByFatigue(opponent.getDefense(), player.getFatigueLevel());
//            case "speed":
//                return reduceByFatigue(opponent.getSpeed(), player.getFatigueLevel());
//            default:
//                return 0;
//        }
//    }

    public static int randomlySomething(int stat) {
        return getRandomBoolean() ? stat * (int) getRandomDouble(0.2, 0.4) : stat * (int) getRandomDouble(0.2, 0.4) * -1;
    }

    public static int getRandomInteger(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static <T> T getRandomElement(List<T> list) {
        return list.get(getRandomInteger(0, list.size()));
    }

    public static double getAddtionalMultiplier(String opponentType) {
        return switch (opponentType.toLowerCase()) {
            case "ogre" -> 0.07;
            case "zombie" -> 0.02;
            case "spider" -> 0.12;
            case "rat" -> 0.16;
            case "goblin" -> 0.04;
            case "witch" -> 0.15;
            default -> 0.00;
        };
    }

    public static String setTextString(String text, String newText) {
        String current = text;
        if (text.split("\n").length > 5) {
            current = text.substring(text.indexOf("\n") + 1);
        }
        return current + "\n" + newText;
    }

    public static void setTextString(Text text, String newText) {
        String current = text.getText();
        if (text.getText().split("\n").length > 5) {
            current = text.getText().substring(text.getText().indexOf("\n") + 1);
        }
        text.setText(current + "\n" + newText);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double round(double value) {
        return round(value, 2);
    }

}
