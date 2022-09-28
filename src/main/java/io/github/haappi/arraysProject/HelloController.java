package io.github.haappi.arraysProject;

import javafx.event.ActionEvent;
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
    private final Object phaseThreeInput = null;
    private final Object phaseFourInput = null;
    private int currentOperation = 2;
    private int phase = 0;
    private Object phaseOneInput = null;
    private Object phaseTwoInput = null;

    /**
     * 1. <span color="green">Find number in an array. If number found, print position and how many. You may not use
     * indexOf. Loops and conditionals only </span><br>
     * 2. <span color="green">Find max, min, average of a group of numbers in an array.</span><br>
     * 3. <span color="red">Add or replaces items in an array at any given location</span><br>
     * 4. <span color="red">Roll x dice y times and display how many of each sum occurs.</span><br>
     * 5. <span color="green">Shuffle an array 2 ways</span><br>
     * 6. <span color="green">Create a new array that translates the letters of the alphabet by a number.</span><br>
     * <span color="green">a. Be sure to create the original array efficiently. Char may be useful instead of String.</span><br>
     * 7. <span color="green">Pascals triangle any row. </span><br>
     * 8. <span color="green">Start with an array of words. User can type any group of letters and just the words either with
     * the letters, beginning with the letters, or ending with the letters are displayed.</span><br>
     * <span color="green">a. Extension to qualify for a possible A—will change the list as you type in a textfield like
     * your phone does. </span><br>
     * 9. <span color="red">Water thins</span>
     */
    private void anotherUselessMethod() throws Exception {
        throw new Exception("have fun debugging this");
    }

    @FXML
    protected void initialize() {
        System.out.println(Utils.rollDiceThing(6, 5));
        System.out.println(getPascalsTriangle(15));
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
                case 5:
                    handleArrayShuffle(content);
                    break;
                case 8:
                    handleArrayOfWords(content);
                    break;
                case 1:
                    handleFindingElement(content);
                    break;
                case 7:
                    handlePascal(content);
                    break;
            }
        }
    }

    private void handlePascal(String current) {
        Integer input = parseInput(current, Integer.class);
        input = input == null ? 4 : input; // if input is null, set it to 4
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

    public void nextStep(ActionEvent actionEvent) { // I am whatsoever not proud of this method.
        if (currentOperation == 6) {
            if (phase == 0) {
                phaseOneInput = parseInput(input.getText());
                handleTextField("Enter the number you want to shift by");
            } else if (phase == 1) {
                phaseTwoInput = parseInput(input.getText(), Integer.class);
                output.setText(translate((String) phaseOneInput, phaseTwoInput == null ? 0 : (Integer) phaseTwoInput));
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
