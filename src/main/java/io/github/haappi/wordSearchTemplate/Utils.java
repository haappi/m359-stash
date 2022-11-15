package io.github.haappi.wordSearchTemplate;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Utils {
    public static String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    public static int getRandInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static ArrayList<String> readFromFileAsArray(String fileName, boolean addResourcesPath) {
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

    public static ArrayList<String> readFromFileAsArray(String fileName) {
        return readFromFileAsArray(fileName, true);
    }

    public static Label[][] fillBoardRadnomly(Label[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getText().equalsIgnoreCase("*")) {
                    board[i][j].setText(alphabet[getRandInt(0, 25)]);
                }
            }
        }
        return board;
    }

    public static Integer estimateDirection(ArrayList<ClickedLetter> letters) {
        if (letters.size() < 2) {
            return null;
        }
        ClickedLetter first = letters.get(0);
        ClickedLetter last = letters.get(letters.size() - 1);

        if (first.getColumn() == last.getColumn() && first.getRow() < last.getRow()) {
            return 0; // down
        } else if (first.getColumn() == last.getColumn() && first.getRow() > last.getRow()) {
            return 1; // up
        } else if (first.getRow() == last.getRow() && first.getColumn() < last.getColumn()) {
            return 2; // right
        } else if (first.getRow() == last.getRow() && first.getColumn() > last.getColumn()) {
            return 3; // left
        } else if (first.getRow() < last.getRow() && first.getColumn() < last.getColumn()) {
            return 4; // down right
        } else if (first.getRow() < last.getRow() && first.getColumn() > last.getColumn()) {
            return 5; // down left
        } else if (first.getRow() > last.getRow() && first.getColumn() < last.getColumn()) {
            return 6; // up right
        } else if (first.getRow() > last.getRow() && first.getColumn() > last.getColumn()) {
            return 7; // up left
        }
        return null;
    }

    /**
     * Edits a {@link Region} in-place with the border shenanigans.
     *
     * @param region       The region to edit.
     * @param top_left     The top left curve thingy. Set to 0 for sharp.
     * @param top_right    The top right curve thingy. Set to 0 for sharp.
     * @param bottom_right The bottom right curve thingy. Set to 0 for sharp.
     * @param bottom_left  The bottom left curve thingy. Set to 0 for sharp.
     */
    public static void setRectangleStyle(Region region, int top_left, int top_right, int bottom_right, int bottom_left) {
        region.setStyle("-fx-background-color: red; -fx-background-radius: " + top_left + " " + top_right + " " + bottom_right + " " + bottom_left + ";");
    }

    public static void fixBoard(ArrayList<ClickedLetter> letters, Label[][] board, GridPane searchBoard) {
        Integer direction = estimateDirection(letters);
        if (direction == null) {
            return;
        }
        ClickedLetter first = letters.get(0);
        ClickedLetter last = letters.get(letters.size() - 1);

        int firstRow = first.getRow();
        int firstColumn = first.getColumn();
        int lastRow = last.getRow();
        int lastColumn = last.getColumn();

        letters.forEach(letter -> searchBoard.getChildren().remove(letter.getRectangle()));
        first = null;
        last = null;
        letters.clear(); // todo fix this somehow not selecting the first, original letter

//        copy.add(new ClickedLetter(board[first.getRow()][firstColumn], copy.size(), direction));
        switch (direction) {
            /*
            The >= | <= is to make sure that the first and last letter are included in the cursor.
             */
            case 0: // down
                for (int i = firstRow; i <= lastRow; i++) {
                    letters.add(new ClickedLetter(board[i][firstColumn], letters.size(), direction));
                }
                break;
            case 1: // up
                for (int i = firstRow; i >= lastRow; i--) {
                    letters.add(new ClickedLetter(board[i][firstColumn], letters.size(), direction));
                }
                break;
            case 2: // right
                for (int i = firstColumn; i <= lastColumn; i++) {
                    letters.add(new ClickedLetter(board[firstRow][i], letters.size(), direction));
                }
                break;
            case 3: // left
                for (int i = firstColumn; i >= lastColumn; i--) {
                    letters.add(new ClickedLetter(board[firstRow][i], letters.size(), direction));
                }
                break;
            case 4: // down right
                for (int i = firstRow, j = firstColumn; i <= lastRow && j <= lastColumn; i++, j++) {
                    letters.add(new ClickedLetter(board[i][j], letters.size(), direction));
                }
                break;
            case 5: // down left
                for (int i = firstRow, j = firstColumn; i <= lastRow && j >= lastColumn; i++, j--) {
                    letters.add(new ClickedLetter(board[i][j], letters.size(), direction));
                }
                break;
            case 6: // up right
                for (int i = firstRow, j = firstColumn; i >= lastRow && j <= lastColumn; i--, j++) {
                    letters.add(new ClickedLetter(board[i][j], letters.size(), direction));
                }
                break;
            case 7: // up left
                for (int i = firstRow, j = firstColumn; i >= lastRow && j >= lastColumn; i--, j--) {
                    letters.add(new ClickedLetter(board[i][j], letters.size(), direction));
                }
                break;
        }
    }


    public static Label[][] fillBoardWithWords(Label[][] board, ArrayList<String> words, GridPane gridPane) {
        for (String word : words) {
            int row = getRandInt(0, board.length - 1);
            int col = getRandInt(0, board[row].length - 1);
            int direction = getRandInt(0, 7);
            while (!canWordBeAdded(direction, row, col, board, word, gridPane)) {
                row = getRandInt(0, board.length - 1);
                col = getRandInt(0, board[row].length - 1);
                direction = getRandInt(0, 7);
            }
            addWordToBoard(direction, row, col, board, word);
        }
        return board;
    }

    public static Label[][] addWordToBoard(int direction, int row, int col, Label[][] board, String word) {
        for (int i = 0; i < word.length(); i++) {
            board[row][col].setText(String.valueOf(word.charAt(i)));
            board[row][col].setTextFill(Color.DARKRED);
            switch (direction) {
                case 0:
                    col--;
                    break;
                case 1:
                    col--;
                    row++;
                    break;
                case 2:
                    row++;
                    break;
                case 3:
                    col++;
                    row++;
                    break;
                case 4:
                    col++;
                    break;
                case 5:
                    col++;
                    row--;
                    break;
                case 6:
                    row--;
                    break;
                case 7:
                    col--;
                    row--;
                    break;
            }
        }
        return board;
    }

    /**
     * I made javadocs for fun. <br>
     * 9 0 1 <br>
     * 8 * 2 <br>
     * 7 6 5 <br>
     *
     * @param direction
     * @param row
     * @param col
     * @param board
     * @param word
     * @param gridPane
     * @return
     */
    public static boolean canWordBeAdded(int direction, int row, int col, Label[][] board, String word, GridPane gridPane) {
        int stringLength = word.length();
        int counter = 0;
        switch (direction) {
            case 0 -> { // up
                if (col - stringLength < 0) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (board[row][col - i].getText().equalsIgnoreCase("*") || board[row][col - i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counter++;
                    }
                }
                return counter == stringLength;
            }
            case 1 -> { // up right
                if (col - stringLength < 0 || row + stringLength > board.length - 1) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (board[row + i][col - i].getText().equalsIgnoreCase("*") || board[row + i][col - i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counter++;
                    }
                }
                return counter == stringLength;
            }
            case 2 -> { // right
                if (row + stringLength > board.length - 1) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (board[row + i][col].getText().equalsIgnoreCase("*") || board[row + i][col].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counter++;
                    }
                }
                return counter == stringLength;
            }
            case 3 -> { // down right
                if (col + stringLength > board[row].length - 1 || row + stringLength > board.length - 1) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (board[row + i][col + i].getText().equalsIgnoreCase("*") || board[row + i][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counter++;
                    }
                }
                return counter == stringLength;
            }
            case 4 -> { // down
                if (col + stringLength > board[row].length - 1) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (board[row][col + i].getText().equalsIgnoreCase("*") || board[row][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counter++;
                    }
                }
                return counter == stringLength;
            }
            case 5 -> { // down left
                if (col + stringLength > board[row].length - 1 || row - stringLength < 0) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (board[row - i][col + i].getText().equalsIgnoreCase("*") || board[row - i][col + i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counter++;
                    }
                }
                return counter == stringLength;
            }
            case 6 -> { // left
                if (row - stringLength < 0) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (board[row - i][col].getText().equalsIgnoreCase("*") || board[row - i][col].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counter++;
                    }
                }
                return counter == stringLength;
            }
            case 7 -> { // up left
                if (col - stringLength < 0 || row - stringLength < 0) {
                    return false;
                }
                for (int i = 0; i < stringLength; i++) {
                    if (board[row - i][col - i].getText().equalsIgnoreCase("*") || board[row - i][col - i].getText().equalsIgnoreCase(String.valueOf(word.charAt(i)))) {
                        counter++;
                    }
                }
                return counter == stringLength;
            }
        }
        return true;
    }

    public static void doEndRectangleFormatting(Region region, Integer direction) {
        if (direction == null) {
            return;
        }
        switch (direction) {
            case 0 -> setRectangleStyle(region, 0, 0, 20, 20);
            case 1 -> setRectangleStyle(region, 20, 20, 0, 0);
            case 2 -> setRectangleStyle(region, 0, 20, 20, 0);
            case 3 -> setRectangleStyle(region, 20, 0, 0, 20);
            case 4 -> setRectangleStyle(region, 0, 0, 20, 20);
            case 5 -> setRectangleStyle(region, 0, 0, 20, 20);
            case 6 -> setRectangleStyle(region, 20, 20, 0, 0);
            case 7 -> setRectangleStyle(region, 20, 20, 0, 0);

        }
    }
}
