package io.github.haappi.arraysProject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

import static io.github.haappi.arraysProject.HelloApplication.validInputTypes;


public class HelloController {

    @FXML
    protected TextField input;
    @FXML
    protected ListView<String> listOutput;
    private int currentOperation = 2;

    /**
     * 1. <span color="yellow">Find number in an array. If number found, print position and how many. You may not use
     * indexOf. Loops and conditionals only </span><br>
     * 2. <span color="yellow">Find max, min, average of a group of numbers in an array.</span><br>
     * 3. <span color="red">Add or replaces items in an array at any given location</span><br>
     * 4. <span color="red">Roll x dice y times and display how many of each sum occurs.</span><br>
     * 5. <span color="yellow">Shuffle an array 2 ways</span><br>
     * 6. <span color="red">Create a new array that translates the letters of the alphabet by a number.</span><br>
     * <span color="orange">a. Be sure to create the original array efficiently. Char may be useful instead of String.</span><br>
     * 7. <span color="red">Pascals triangle any row. </span><br>
     * 8. <span color="red">Start with an array of words. User can type any group of letters and just the words either with
     * the letters, beginning with the letters, or ending with the letters are displayed.</span><br>
     * <span color="orange">a. Extension to qualify for a possible A—will change the list as you type in a textfield like
     * your phone does. </span><br>
     * 9. <span color="red">Locker Simulation</span>
     */
    private void anotherUselessMethod() throws Exception {
        throw new Exception("have fun debugging this");
    }

    @FXML
    protected void findElem() {
        int[] array = {2, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        System.out.println(Utils.getCountAndPositionOf(2, array));
    }

    @FXML
    protected void minMaxArray() {
        int[] array = {2, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        System.out.println(Utils.minMaxArray(array));
    }

    @FXML
    protected void shuffleAnArray() {
        ArrayList<String> nrowww = new ArrayList<>();
        nrowww.add("b");
        nrowww.add("ba");
        nrowww.add("a");
        nrowww.add("bdef");
        nrowww.add("c");

        System.out.println(Utils.shuffleAnArray(nrowww));
    }


    @FXML
    protected void onKeyPress(KeyEvent inputMethodEvent) {
        if (validInputTypes.contains(this.currentOperation)) {
            System.out.println("hi");
        }
        System.out.println(((TextField) inputMethodEvent.getSource()).getText());
    }
}
