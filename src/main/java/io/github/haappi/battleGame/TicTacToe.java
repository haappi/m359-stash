package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class TicTacToe {
    private final int[][] board = new int[3][3];
    @FXML protected TextField betAmountInput;
    @FXML protected Button resetGameButton;

    @FXML protected Text balance, gameState;
    private Double betAmount;
    Player playerInstance = HelloApplication.getInstance().getPlayer();

    private boolean player1Turn = true; // true = Player X, false = Player O


    @FXML
    protected Button one, two, three, four, five, six, seven, eight, nine;
    private final ArrayList<Button> buttons = new ArrayList<>();

    @FXML protected void gamePress(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        setButtonStuff(button);
        lookAtWinStuff();
        scuffedComputerTurn();
    }

    private void lookAtWinStuff() {
        int win = checkWin();
        if (win == 1) { // Player won
            playerInstance.addToBankBalance(betAmount * 2); // todo fix this adding weird
        } else if (win == -1) { // Computer won
            playerInstance.addToBankBalance(-betAmount * 0.25);
        } else if (win == 0) { // Draw
            playerInstance.setBankBalance(playerInstance.getBankBalance() - (betAmount / 2));
        }
        balance.setText("Balance: " + playerInstance.getBankBalance());
    }

    private void scuffedComputerTurn() {
        int randomNumber = Utils.getRandomInteger(0, 8);
        Button button = buttons.get(randomNumber);
        if (button.getText().equals("_")) {
            setButtonStuff(button);
        } else {
            scuffedComputerTurn();
        }
        lookAtWinStuff();
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
        gameState.setText(player1Turn ? "Your turn" : "Computer's turn");
    }

    private void setAllButtons(boolean status) {
        one.setDisable(status);
        two.setDisable(status);
        three.setDisable(status);
        four.setDisable(status);
        five.setDisable(status);
        six.setDisable(status);
        seven.setDisable(status);
        eight.setDisable(status);
        nine.setDisable(status);
    }

    private int checkWin() {
        // check horizontal
        for (int[] intArray : board) {
            int sum = sum(intArray);
            if (sum == 3) {
                gameState.setText("You win!");
                setAllButtons(true);
                return 1;
            } else if (sum == -3) {
                gameState.setText("Computer wins!");
                setAllButtons(true);
                return -1;
            }
        }

        // check vertical
        for (int i = 0; i < 3; i++) {
            int sum = board[0][i] + board[1][i] + board[2][i];
            if (sum == 3) {
                gameState.setText("You win!");
                setAllButtons(true);
                return 1;
            } else if (sum == -3) {
                gameState.setText("Computer wins!");
                setAllButtons(true);
                return -1;
            }
        }

        // check diagonal
        int sum = board[0][0] + board[1][1] + board[2][2];
        if (sum == 3) {
            gameState.setText("You win!");
            setAllButtons(true);
            return 1;
        } else if (sum == -3) {
            gameState.setText("Computer wins!");
            setAllButtons(true);
            return -1;
        }

        sum = board[0][2] + board[1][1] + board[2][0];
        if (sum == 3) {
            gameState.setText("You win!");
            setAllButtons(true);
            return 1;
        } else if (sum == -3) {
            gameState.setText("Computer wins!");
            setAllButtons(true);
            return -1;
        }

        // check if draw
        if (isBoardFilled()) {
            gameState.setText("Draw!");
            setAllButtons(true);
            return 0;
        }
        return 2; // game not over
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

        buttons.addAll(List.of(one, two, three, four, five, six, seven, eight, nine));
        balance.setText("Balance: " + playerInstance.getBankBalance());
        setAllButtons(true);
    }


    @FXML protected void mainMenu(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("main-menu");
    }

    @FXML protected void betAmount(ActionEvent actionEvent) {
        TextField button = (TextField) actionEvent.getSource();
        try {
            betAmount = Double.parseDouble(button.getText());
            if (betAmount < 0) {
                gameState.setText("Yeah luckily I can debug.");
                betAmount = null;
            } else {
                if (betAmount > playerInstance.getBankBalance()) {
                    gameState.setText("You don't have enough money!");
                    betAmount = null;
                } else {
                    playerInstance.setBankBalance(playerInstance.getBankBalance() - betAmount);
                    balance.setText("Balance: " + playerInstance.getBankBalance());
                    gameState.setText("You just put down " + betAmount + " on the game. Your turn.");
                    button.setDisable(true);
                    setAllButtons(false);

                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private int sum(int[] things) {
        int sum = 0;
        for (int thing : things) {
            sum += thing;
        }
        return sum;
    }

    public void resetGame(ActionEvent actionEvent) {
        for (Button button : buttons) {
            if (!button.isDisable()) {
                return;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
        for (Button button : buttons) {
            button.setText("_");
            button.setTextFill(Color.BLACK);
            button.setDisable(false);
        }
        gameState.setText("Place your bet!");
        betAmountInput.setDisable(false);


    }
}
