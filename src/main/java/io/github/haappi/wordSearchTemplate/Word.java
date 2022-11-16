package io.github.haappi.wordSearchTemplate;

import java.util.HashMap;

public class Word {
    private final String word;
    private final HashMap<Integer, Integer> positions;

    public Word(HashMap<Integer, Integer> rowCols, String word) {
        this.positions = rowCols;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public HashMap<Integer, Integer> getPositions() {
        return positions;
    }

    public String toString() {
        return this.word;
    }
}
