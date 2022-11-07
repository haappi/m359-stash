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
            int direction = getRandInt(2, 2);
            while (!canWordBeAdded(direction, row, col, board, word, gridPane)) {
                row = getRandInt(0, board.length - 1);
                col = getRandInt(0, board[row].length - 1);
                direction = getRandInt(2, 2);
            }
            addWordToBoard(direction, row, col, board, word);
        }
        return board;
    }

    public static Text[][] addWordToBoard(int direction, int row, int col, Text[][] board, String word) {
        for (int i = 0; i < word.length(); i++) {
            board[row][col].setText(String.valueOf(word.charAt(i)));
            board[row][col].setFill(Color.DARKRED);
//            switch (direction) {
//                case 0:
//                    col--;
//                    break;
//                case 1:
//                    col--;
//                    row++;
//                    break;
//                case 2:
//                    row++;
//                    break;
//                case 3:
//                    col++;
//                    row++;
//                    break;
//                case 4:
//                    col++;
//                    break;
//                case 5:
//                    col++;
//                    row--;
//                    break;
//                case 6:
//                    row--;
//                    break;
//                case 7:
//                    col--;
//                    row--;
//                    break;
//            }
            switch (direction) {
                case 0:
                    row--;
                    break;
                case 1:
                    row--;
                    col++;
                    break;
                case 2:
                    col++;
                    break;
                case 3:
                    row++;
                    col++;
                    break;
                case 4:
                    row++;
                    break;
                case 5:
                    row++;
                    col--;
                    break;
                case 6:
                    col--;
                    break;
                case 7:
                    row--;
                    col--;
                    break;
            }
        }
        return board;
    }

    public static boolean canWordBeAdded(int direction, int row, int col, Text[][] board, String word, GridPane gridPane) {
        int stringLength = word.length();
        switch (direction) {
            case 0 -> { // up
                if (col - stringLength < 0) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (!board[row][col + i].getText().equalsIgnoreCase("*") && !board[row][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
            } // fixme literally fix all of these damn cases
            case 1 -> { // up right
                if (col + stringLength > board[row].length || row - stringLength < 0) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (!board[row - i][col + i].getText().equalsIgnoreCase("*") && !board[row - i][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
            }
            case 2 -> { // right
                if (row + stringLength > board.length) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (!board[row - i][col].getText().equalsIgnoreCase("*") && !board[row - i][col].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
            }
            case 3 -> { // down right
                if (col + stringLength > board[row].length || row + stringLength > board.length) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (!board[row + i][col + i].getText().equalsIgnoreCase("*") && !board[row + i][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
            }
            case 4 -> { // down
                if (col + stringLength > board[row].length) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (!board[row][col + i].getText().equalsIgnoreCase("*") && !board[row][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
            }
            case 5 -> { // down left
                if (col - stringLength < 0 || row + stringLength > board.length) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (!board[row + i][col - i].getText().equalsIgnoreCase("*") && !board[row + i][col - i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
            }
            case 6 -> { // left
                if (row - stringLength < 0) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (!board[row + i][col].getText().equalsIgnoreCase("*") && !board[row + i][col].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
            }
            case 7 -> { // up left
                if (col - stringLength < 0 || row - stringLength < 0) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (!board[row - i][col - i].getText().equalsIgnoreCase("*") && !board[row - i][col - i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        return false;
                    }
                }
            }

        }
        return true;
    }
}
