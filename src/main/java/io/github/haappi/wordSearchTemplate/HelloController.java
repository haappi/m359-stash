package io.github.haappi.wordSearchTemplate;

import static io.github.haappi.wordSearchTemplate.Utils.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class HelloController {
    private static final Timer timer = new Timer();
    private final ArrayList<String> listOfPossibleWords = new ArrayList<>();
    public RadioButton easyRadio;
    public RadioButton mediumRadio;
    public RadioButton hardRadio;
    public Button startButton;
    public Text wordSearch;
    public Text scoreLabel;
    public TextField playerNameInput;
    @FXML protected Text timeElapsed;
    @FXML protected GridPane wordBank;
    @FXML protected Button hintButtonVariable;
    @FXML protected GridPane searchBoard;
    @FXML protected AnchorPane anchorPane;
    Label[][] listOWords;
    ArrayList<ClickedLetter> clickedLetters = new ArrayList<>();
    private double score = 0;
    private long programStartTime;
    private int difficulty = 1;
    private String playerName;
    private ArrayList<Word> hintWords = new ArrayList<>();
    private ClickedLetter currentLetter;
    private ArrayList<Node> stuffToToggle;

    public static void cancel_timer() {
        timer.cancel();
    }

    @FXML
    protected void initialize() {
        stuffToToggle =
                new ArrayList<>(
                        List.of(
                                easyRadio,
                                mediumRadio,
                                hardRadio,
                                hintButtonVariable,
                                timeElapsed,
                                startButton,
                                wordSearch,
                                scoreLabel));

        searchBoard.setOnDragDetected(
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        searchBoard.startFullDrag();
                    }
                });
        ArrayList<String> words = Utils.readFromFileAsArray("ok.txt");
        words.replaceAll(String::toUpperCase); // Calls toUpperCase on each String in the array.
        listOfPossibleWords.addAll(words);
    }

    @FXML
    protected void handleClickMe() {
        for (Node node : stuffToToggle) {
            node.setVisible(!node.isVisible());
        }
        playerName = playerNameInput.getText() != null ? playerNameInput.getText() : "Player";

        programStartTime = System.currentTimeMillis();
        hintButtonVariable.setVisible(true);
        listOWords = new Label[25][25];

        for (int i = 0; i < listOWords.length; i++) {
            for (int j = 0; j < listOWords[i].length; j++) {
                Label label = new Label("*");

                label.setPrefSize(30, 30);
                label.setFont(Font.font(20));
                label.setTextAlignment(TextAlignment.CENTER);
                label.setAlignment(Pos.CENTER);
                label.setContentDisplay(ContentDisplay.CENTER);

                label.setOnMouseDragOver(
                        event -> {
                            if ((currentLetter != null ? currentLetter.getLabel() : null)
                                    == label) {
                                return;
                            }
                            currentLetter = new ClickedLetter(label, clickedLetters.size(), -1);
                            // todo make a way so that the user can't click on a letter that is not
                            // next to the last clicked letter.
                            // todo also a way that i can dynamically change the line in the 8
                            // directions.
                            clickedLetters.add(
                                    currentLetter); // this has to be before the line after as i
                            // save the color while initializing it.
                            fixBoard(clickedLetters, listOWords, searchBoard);
                            if (clickedLetters.size() > 2) {
                                doEndRectangleFormatting(
                                        clickedLetters.get(clickedLetters.size() - 1).getRegion(),
                                        estimateDirection(clickedLetters));
                            }
                        });
                listOWords[i][j] = label;
                // basically the same as setting the onAction for the button, but only for a drag,
                // an d doesn't require me to create a whole variable for it
                // used this to get the specific event i should be using:
                // https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
                searchBoard.add(label, j, i);
            }
        }
        hintWords = fillBoardWithWords(listOWords, listOfPossibleWords, searchBoard);
        listOfPossibleWords.forEach(
                word -> {
                    Text label =
                            new Text(
                                    word.toLowerCase().substring(0, 1).toUpperCase()
                                            + word.toLowerCase().substring(1));
                    label.setFont(Font.font(17));
                    label.setTextAlignment(TextAlignment.CENTER);
                    wordBank.add(label, 0, wordBank.getChildren().size());
                    label.setFill(Color.RED);
                });
        Utils.fillBoardRadnomly(listOWords);

        anchorPane.setOnMouseDragExited( // I can add events to the AnchorPane also, and seeing
                // whenever I released the drag button
                eventt -> { // i made this on my own. no complaints
                    StringBuilder sb = new StringBuilder();
                    Integer direction = estimateDirection(clickedLetters);
                    clickedLetters.forEach(
                            clickedLetter -> {
                                sb.append(clickedLetter.getLabel().getText().toUpperCase());
                                this.searchBoard.getChildren().remove(clickedLetter.getRegion());
                            });
                    String word = sb.toString();
                    String reversed = sb.reverse().toString();
                    if (listOfPossibleWords.contains(word)
                            || listOfPossibleWords.contains(reversed)) {
                        clickedLetters.forEach(
                                clickedLetter -> {
                                    new ClickedLetter(
                                            clickedLetter.getLabel(),
                                            clickedLetters.size(),
                                            direction,
                                            "pink");
                                    if (clickedLetters.get(clickedLetters.size() - 1)
                                            == clickedLetter) {
                                        // todo somehow apply correct formatting to the
                                        // first & last letter
                                        //
                                        doEndRectangleFormatting(
                                                clickedLetter.getRegion(), direction);
                                    }
                                    listOfPossibleWords.remove(word);
                                    listOfPossibleWords.remove(reversed);
                                    dictionary.remove(word);
                                    dictionary.remove(reversed);
                                    score = score + (word.length() * 0.6) - difficulty;

                                    hintWords.removeIf(
                                            word1 ->
                                                    word1.getWord().equals(word)
                                                            || word1.getWord()
                                                                    .equals(reversed)); // basically
                                    // remove,
                                    // but with
                                    // a
                                    // one-liner
                                    // checker.
                                    wordBank.getChildren()
                                            .forEach(
                                                    node -> {
                                                        if (((Text) node)
                                                                .getText()
                                                                .equals(
                                                                        word.toLowerCase()
                                                                                        .substring(
                                                                                                0,
                                                                                                1)
                                                                                        .toUpperCase()
                                                                                + word.toLowerCase()
                                                                                        .substring(
                                                                                                1))) {
                                                            ((Text) node).setStrikethrough(true);
                                                            ((Text) node).setFill(Color.GREEN);
                                                        }
                                                    });
                                });
                    } else if (dictionary.containsKey(word) || dictionary.containsKey(reversed)) {
                        dictionary.remove(word);
                        dictionary.remove(reversed);
                        clickedLetters.forEach(
                                clickedLetter ->
                                        new ClickedLetter(
                                                clickedLetter.getLabel(),
                                                clickedLetters.size(),
                                                direction,
                                                "black"));
                        score = score + 2;
                    } else {
                        clickedLetters.forEach(
                                clickedLetter ->
                                        clickedLetter
                                                .getLabel()
                                                .setTextFill(clickedLetter.getOldColor()));
                    }
                    clickedLetters.clear();

                    if (listOfPossibleWords.size() == 0) {
                        Path path = Paths.get("src/main/resources/leaderboard.txt");
                        timer.cancel();

                        try (BufferedWriter writer =
                                Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                            writer.write("\n");
                            writer.write(
                                    playerName
                                            + " "
                                            + score
                                            + " "
                                            + difficulty
                                            + " "
                                            + timeElapsed.getText());
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Game Over");
                        alert.setHeaderText("You have won!");
                        alert.setContentText("Your score is: " + score);
                        alert.showAndWait();

                        HelloApplication.setStageScene("main-menu");
                    }
                });
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        String currentTime = timeElapsed.getText();
                        int minutes = Integer.parseInt(currentTime.split(":")[0]);
                        int seconds = Integer.parseInt(currentTime.split(":")[1]);
                        seconds++;
                        if (seconds == 60) {
                            seconds = 0;
                            minutes++;
                        }
                        timeElapsed.setText(String.format("%02d:%02d", minutes, seconds));
                        if (seconds == 10) {
                            hintButtonVariable.setDisable(getRandInt(1, 10) > 8);
                        }
                        score = (round(score));
                        scoreLabel.setText(score + "");
                    }
                },
                1L,
                1000L); // every second
    }

    @FXML
    protected void hintButton() {
        if (hintWords.size() == 0) {
            return;
        }
        score = score - 6;
        Word word = hintWords.get(Utils.getRandInt(0, hintWords.size()));
        hintWords.remove(word);
        String wordString = word.getWord();
        for (String entry : word.getPositions()) {
            int row = Integer.parseInt(entry.split(";")[0]);
            int col = Integer.parseInt(entry.split(";")[1]);
            searchBoard
                    .getChildren()
                    .forEach(
                            node -> {
                                if (GridPane.getRowIndex(node) == row
                                        && GridPane.getColumnIndex(node) == col) {
                                    ((Label) node).setTextFill(Color.WHITE);
                                    node.setStyle("-fx-background-color: black");
                                }
                            });
        }

        wordBank.getChildren()
                .forEach(
                        nodee -> {
                            if (((Text) nodee)
                                    .getText()
                                    .equals(
                                            wordString.toLowerCase().substring(0, 1).toUpperCase()
                                                    + wordString.toLowerCase().substring(1))) {
                                ((Text) nodee).setStrikethrough(true);
                                ((Text) nodee).setFill(Color.BLACK);
                            }
                        });
    }

    public void easySel(ActionEvent actionEvent) {
        mediumRadio.setSelected(false);
        hardRadio.setSelected(false);
        difficulty = 1;
    }

    public void mediumSel(ActionEvent actionEvent) {
        easyRadio.setSelected(false);
        hardRadio.setSelected(false);
        difficulty = 2;
    }

    public void hardSel(ActionEvent actionEvent) {
        easyRadio.setSelected(false);
        mediumRadio.setSelected(false);
        difficulty = 3;
    }

    public void exitGame(ActionEvent event) {
        HelloApplication.setStageScene("main-menu");
    }

    public void diySearch(ActionEvent event) {
        System.out.println("Type 'end' to end the entry.");
        Scanner in = new Scanner(System.in);
        listOfPossibleWords.clear();
        while (in.hasNext()) {
            String word = in.next();
            if (word.equals("end")) {
                break;
            }
            if (listOfPossibleWords.contains(word)) {
                continue;
            }
            listOfPossibleWords.add(word.toUpperCase());
        }
        ((Button) event.getSource()).setVisible(false);
    }
}
