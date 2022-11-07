package io.github.haappi.wordSearchTemplate;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Utils {
    public static String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    public static int getRandInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static ArrayList<String> getWordBankFromFileAsArray(String fileName, boolean addResourcesPath) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<String> getWordBankFromFileAsArray(String fileName) {
        return getWordBankFromFileAsArray(fileName, true);
    }

    public static Text[][] fillBoardRadnomly(Text[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getText().equalsIgnoreCase("*")) {
                    board[i][j].setText(alphabet[getRandInt(0, 25)]);
                }
            }
        }
        return board;
    }

    public static Text[][] fillBoardWithWords(Text[][] board, ArrayList<String> words, GridPane gridPane) {
        for (String word : words) {
            int row = getRandInt(0, board.length - 1);
            int col = getRandInt(0, board[row].length - 1);
            int direction = getRandInt(0, 0);
            while (!canWordBeAdded(direction, row, col, board, word, gridPane)) {
                row = getRandInt(0, board.length - 1);
                col = getRandInt(0, board[row].length - 1);
                direction = getRandInt(0, 0);
            }
            addWordToBoard(direction, row, col, board, word);
        }
        return board;
    }

    public static void addWordToBoard(int direction, int row, int col, Text[][] board, String word) {
        for (int i = 0; i < word.length(); i++) {
            board[row][col].setText(String.valueOf(word.charAt(i)));
            board[row][col].setFill(Color.DARKRED);
            switch (direction) { // fixme
                /*
                For some reason, 0 is going to the left and not up.
                 */
                case 0 -> col--; // up
                case 1 -> { // up right
                    row++;
                    col--;
                }
                case 2 -> row++; // right
                case 3 -> { // down right
                    row++;
                    col++;
                }
                case 4 -> col++; // down
                case 5 -> { // down left
                    row--;
                    col++;
                }
                case 6 -> row--; // left
                case 7 -> { // up left
                    row--;
                    col--;
                }

            }
        }
    }

    public static boolean canWordBeAdded(int direction, int row, int col, Text[][] board, String word, GridPane gridPane) {
        int stringLength = word.length();
        switch (direction) {
            case 0 -> { // Up
                if (col - stringLength < 0) {
                    return false;
                }
                int counterToCheck = 0;
                for (int i = 0; i < stringLength; i++) {
                    System.out.println(word.charAt(i));
                    if (board[row][col - i].getText().equalsIgnoreCase("*") || board[row][col - i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counterToCheck++;
                    }
                }
                return counterToCheck == stringLength;
            }
            case 1 -> { // Up Right
                if (col - stringLength < 0 || row + stringLength > board.length - 1) {
                    return false;
                }
                int counterToCheck = 0;
                for (int i = 0; i < stringLength; i++) {
                    if (board[row + i][col - i].getText().equalsIgnoreCase("*") || board[row + i][col - i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counterToCheck++;
                    }
                }
                return counterToCheck == stringLength;
            }
            case 2 -> { // Right
                if (row + stringLength > board.length - 1) {
                    return false;
                }
                int counterToCheck = 0;
                for (int i = 0; i < stringLength; i++) {
                    if (board[row + i][col].getText().equalsIgnoreCase("*") || board[row + i][col].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counterToCheck++;
                    }
                }
                return counterToCheck == stringLength;
            }
            case 3 -> { // Down Right
                if (col + stringLength > board[row].length - 1 || row + stringLength > board.length - 1) {
                    return false;
                }
                int counterToCheck = 0;
                for (int i = 0; i < stringLength; i++) {
                    if (board[row + i][col + i].getText().equalsIgnoreCase("*") || board[row + i][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counterToCheck++;
                    }
                }
                return counterToCheck == stringLength;
            }
            case 4 -> { // Down
                if (col + stringLength > board[row].length - 1) {
                    return false;
                }
                int counterToCheck = 0;
                for (int i = 0; i < stringLength; i++) {
                    if (board[row][col + i].getText().equalsIgnoreCase("*") || board[row][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counterToCheck++;
                    }
                }
                return counterToCheck == stringLength;
            }
            case 5 -> { // Down Left
                if (col + stringLength > board[row].length - 1 || row - stringLength < 0) {
                    return false;
                }
                int counterToCheck = 0;
                for (int i = 0; i < stringLength; i++) {
                    if (board[row - i][col + i].getText().equalsIgnoreCase("*") || board[row - i][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counterToCheck++;
                    }
                }
                return counterToCheck == stringLength;
            }
            case 6 -> { // Left
                if (row - stringLength < 0) {
                    return false;
                }
                int counterToCheck = 0;
                for (int i = 0; i < stringLength; i++) {
                    if (board[row - i][col].getText().equalsIgnoreCase("*") || board[row - i][col].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counterToCheck++;
                    }
                }
                return counterToCheck == stringLength;
            }
            case 7 -> { // Up Left
                if (col - stringLength < 0 || row - stringLength < 0) {
                    return false;
                }
                int counterToCheck = 0;
                for (int i = 0; i < stringLength; i++) {
                    if (board[row - i][col - i].getText().equalsIgnoreCase("*") || board[row - i][col - i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counterToCheck++;
                    }
                }
                return counterToCheck == stringLength;
            }
        }
        return true;
    }
}
