package io.github.haappi.arraysProject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.arraysProject.HelloApplication.dictionary;
import static io.github.haappi.arraysProject.HelloApplication.validInputTypes;
import static io.github.haappi.arraysProject.Utils.*;


public class HelloController {

    @FXML
    protected Text output;
    @FXML
    protected TextField input;
    @FXML
    protected ListView<String> listOutput;
    private int currentOperation = 2;
    private int phase = 0;
    private Object phaseOneInput = null;
    private Object phaseTwoInput = null;


    @FXML
    protected void diceRoll() {
        handleTextField(4, "How many dice do you want to roll?");
    }

    @FXML
    protected void addRemove() {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandomNumber(1, 6);
        }
        this.phaseOneInput = array;
        handleTextField(3, "add|replace;index;value");
    }

    @FXML
    protected void findElem() {
        int[] array = new int[25];
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandomNumber(1, 5);
        }
        this.phaseOneInput = array;
        handleTextField(1, "Enter the element you want to find.\n" + beautifyArray(array) + "\n");
    }

    @FXML
    protected void minMaxArray() {
        currentOperation = 2;
        int[] array = new int[25];
        for (int i = 0; i < array.length; i++) {
            array[i] = getRandomNumber(1, 100);
        }
        output.setText(Utils.minMaxArray(array));
    }

    @FXML
    protected void waterDaPlants() {
        currentOperation = 9;
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ints.add(Utils.getRandomNumber(1, 10));
        }
        output.setText(Utils.waterThing(ints));
        output.setText(output.getText() + "\n" + ints);
    }

    @FXML
    protected void shiftCharacters() {
        handleTextField(6, "Enter the string you want to shift");
    }

    @FXML
    protected void pascal() {
        handleTextField(7, "Enter the number of rows you want to find.");
    }

    @FXML
    protected void shuffleAnArray() {
        handleTextField(5, "Enter the array you want to shuffle.");
    }

    @FXML
    protected void arrayOfWords() {
        currentOperation = 8;
        input.clear();
        input.setPromptText("Start entering some characters.");
        listOutput.getItems().addAll(HelloApplication.dictionarySet.stream().toList());
    }

    @FXML
    protected void onKeyPress(KeyEvent inputMethodEvent) {
        if (validInputTypes.contains(this.currentOperation)) {
            String content = getStringFrom(inputMethodEvent);
            switch (this.currentOperation) {
                case 5 -> handleArrayShuffle(content);
                case 8 -> handleArrayOfWords(content);
                case 1 -> handleFindingElement(content);
                case 7 -> handlePascal(content);
                case 3 -> handleAddingRemoving(content);
            }
        }
    }

    private void handleAddingRemoving(String content) {
        String[] split = content.split(";");
        if (split.length != 3) {
            output.setText("Invalid input");
            return;
        }
        String operation = split[0];
        int index;
        int value;
        try {
            index = Integer.parseInt(split[1]);
            value = Integer.parseInt(split[2]);
        } catch (NumberFormatException e) {
            output.setText("NaN (Not a number)!");
            return;
        }

        int[] array = (int[]) this.phaseOneInput;
        if (operation.equalsIgnoreCase("add")) {
            array = Utils.addElementToArrayAtAGivenPosition(array, index, value);
            output.setText(null);
        } else if (operation.equalsIgnoreCase("replace")) {
            if (index >= array.length) {
                output.setText("Index out of bounds");
                return;
            }
            array = Utils.replaceElementInArray(array, index, value);
            output.setText(null);
        } else {
            handleTextField(3, "add|replace;index;value");
            output.setText("Invalid input");
            return;
        }
        this.phaseOneInput = array;
        output.setText(output.getText() + "\n" + beautifyArray(array));
    }

    private void handlePascal(String current) {
        Integer input = parseInput(current, Integer.class);
        input = input == null ? 2 : input; // if input is null, set it to 2
        output.setText(getPascalsTriangle(input));
    }

    private void handleFindingElement(String current) {
        Integer input = parseInput(current, Integer.class);
        input = input == null ? 0 : input; // if input is null, set it to 0
        String originalInput = output.getText().split("\n")[0] + "\n" + output.getText().split("\n")[1] + "\n";
        output.setText(originalInput + "\n" + getCountAndPositionOf(input, (int[]) this.phaseOneInput));
    }

    private void handleArrayShuffle(String current) {
        ArrayList<String> arrayList = new ArrayList<>(List.of(current.split(" ")));
        output.setText(beautifyArray(Utils.shuffleAnArray(arrayList)) + "\n" + beautifyArray(Utils.flipAnArray(arrayList)));
    }

    private void handleArrayOfWords(String current) {
        listOutput.getItems().clear();
        listOutput.getItems().addAll(HelloApplication.dictionarySet.stream().filter(s -> s.toLowerCase().startsWith(current.toLowerCase())).toList());
        // this "streams" the items, (think for loop) and has a filter that checks if the word starts with the current input.
        // the -> is a lambda expression, which is a way to write a function in one line, without declaring it.
    }

    @FXML
    protected void listViewClicked(MouseEvent mouseEvent) {
        ListView<String> source = (ListView<String>) mouseEvent.getSource();
        String selectedItem = source.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            output.setText(dictionary.getOrDefault(selectedItem, "No definition found."));
        }
    }

    public void nextStep() { // I am whatsoever not proud of this method.
        if (currentOperation == 6) {
            if (phase == 0) {
                phaseOneInput = parseInput(input.getText());
                handleTextField("Enter the number you want to shift by");
                phase++;
            } else if (phase == 1) {
                phaseTwoInput = parseInput(input.getText(), Integer.class);
                output.setText(translate((String) phaseOneInput, phaseTwoInput == null ? 0 : (Integer) phaseTwoInput));
            }
        } else if (currentOperation == 4) {
            if (phase == 0) {
                phaseOneInput = parseInput(input.getText(), Integer.class);
                handleTextField("How many times do you want to roll the dice?");
                phase++;
            } else if (phase == 1) {
                phaseTwoInput = parseInput(input.getText(), Integer.class);
                output.setText(Utils.rollDiceThing((Integer) phaseOneInput, phaseTwoInput == null ? 1 : (Integer) phaseTwoInput));
            }
        }
    }

    private void handleTextField(Integer currentOperation, String message) {
        handleTextField(currentOperation, message, 0);
    }

    private void handleTextField(Integer currentOperation, String message, Integer phase) {
        this.currentOperation = currentOperation;
        this.phase = phase;
        input.clear();
        input.setPromptText(message);
        output.setText(message);
    }

    private void handleTextField(String message) {
        handleTextField(this.currentOperation, message, this.phase);
    }
}
