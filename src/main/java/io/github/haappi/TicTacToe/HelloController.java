package io.github.haappi.TicTacToe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HelloController {
    private final int[][] board = new int[3][3];
    @FXML
    protected Text gameStatus;
    private boolean player1Turn = true; // true = Player X, false = Player O
    @FXML
    private Button one, two, three, four, five, six, seven, eight, nine;

    @FXML
    protected void initialize() {
        one.setId("0_0");
        two.setId("0_1");
        three.setId("0_2");
        four.setId("1_0");
        five.setId("1_1");
        six.setId("1_2");
        seven.setId("2_0");
        eight.setId("2_1");
        nine.setId("2_2");
    }


    private void setButtonStuff(Button button) {
        String[] id = button.getId().split("_");
        int x = Integer.parseInt(id[0]);
        int y = Integer.parseInt(id[1]);
        board[x][y] = player1Turn ? 1 : -1;
        button.setText(player1Turn ? "X" : "O");
        button.setDisable(true);
        button.setTextFill(player1Turn ? Color.RED : Color.BLUE);
        player1Turn = !player1Turn;
        gameStatus.setText(player1Turn ? "Player X's turn" : "Player O's turn");

        printBoardNicely();
    }


    @FXML
    protected void buttonPushed(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        setButtonStuff(button);
        checkWin();
    }

    @FXML
    protected void nameInputted(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        setButtonStuff(button);
        checkWin();
    }

    private void checkWin() {
        // check horizontal
        for (int[] intArray : board) {
            int sum = sum(intArray);
            if (sum == 3) {
                gameStatus.setText("Player X wins!");
                disableButtons();
            } else if (sum == -3) {
                gameStatus.setText("Player O wins!");
                disableButtons();
            }
        }

        // check vertical
        for (int i = 0; i < 3; i++) {
            int sum = board[0][i] + board[1][i] + board[2][i];
            if (sum == 3) {
                gameStatus.setText("Player X wins!");
                disableButtons();
            } else if (sum == -3) {
                gameStatus.setText("Player O wins!");
                disableButtons();
            }
        }

        // check diagonal
        int sum = board[0][0] + board[1][1] + board[2][2];
        if (sum == 3) {
            gameStatus.setText("Player X wins!");
            disableButtons();
        } else if (sum == -3) {
            gameStatus.setText("Player O wins!");
            disableButtons();
        }

        sum = board[0][2] + board[1][1] + board[2][0];
        if (sum == 3) {
            gameStatus.setText("Player X wins!");
            disableButtons();
        } else if (sum == -3) {
            gameStatus.setText("Player O wins!");
            disableButtons();
        }

        // check if draw
        if (isBoardFilled()) {
            gameStatus.setText("Draw!");
            disableButtons();
        }
    }

    private boolean isBoardFilled() {
        for (int[] intArray : board) {
            for (int i : intArray) {
                if (i == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    private void disableButtons() {
        one.setDisable(true);
        two.setDisable(true);
        three.setDisable(true);
        four.setDisable(true);
        five.setDisable(true);
        six.setDisable(true);
        seven.setDisable(true);
        eight.setDisable(true);
        nine.setDisable(true);
    }

    private void printBoardNicely() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int sum(int[] things) {
        int sum = 0;
        for (int thing : things) {
            sum += thing;
        }
        return sum;
    }
}