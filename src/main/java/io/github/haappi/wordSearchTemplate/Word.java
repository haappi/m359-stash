package io.github.haappi.wordSearchTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Word {
    private final String word;
    private final ArrayList<String> positions;

    public Word(String[] rowCols, String word) {
        this.positions = new ArrayList<>(Arrays.asList(rowCols));
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public ArrayList<String> getPositions() {
        return positions;
    }

    public String toString() {
           return "Word{" +
                    "word='" + word + '\'' +
                    ", positions=" + positions +
                    '}';
    }
}
