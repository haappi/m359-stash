package io.github.haappi.wordSearchTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Utils {
    public static String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    public static int getRandInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static ArrayList<String> getWordBankFromFileAsArray(String fileName, boolean addResourcesPath) throws IOException {
        fileName = Paths.get(fileName).toString();
        if (addResourcesPath) {
            fileName = String.valueOf(Paths.get("src/main/resources/" + fileName));
        }
        if (!Files.exists(Paths.get(fileName))) {
            return null;
        }
        ArrayList<String> words = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get(fileName))) {
            words.add(line.toUpperCase());
        }
        return words;
    }

    public static ArrayList<String> getWordBankFromFileAsArray(String fileName) throws IOException {
        return getWordBankFromFileAsArray(fileName, true);
    }
}
