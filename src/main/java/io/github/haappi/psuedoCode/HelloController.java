package io.github.haappi.psuedoCode;

import static io.github.haappi.psuedoCode.Common.arrayToString;
import static io.github.haappi.psuedoCode.Common.parseJOptionInput;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javax.swing.*;

public class HelloController {

  private String generateFibonacci(int count) {
    count++;
    List<Integer> numbers = new ArrayList<>();
    numbers.add(0);
    numbers.add(1);
    for (int i = 2; i < count; i++) {
      numbers.add(numbers.get(i - 2) + numbers.get(i - 1)); // add the last two numbers together
    } // todo fix the output thing not working across the 20 different types of stuipd things i have
    return arrayToString(numbers);
  }

  @FXML
  protected void doFibonacci() {
    Integer inputDialog =
        parseJOptionInput(JOptionPane.showInputDialog("How many things "), Integer.class);
    if (inputDialog == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    System.out.println(generateFibonacci(inputDialog));
  }

  @FXML
  protected void doMultiples() {
    Integer instances =
        parseJOptionInput(JOptionPane.showInputDialog("How many instances? "), Integer.class);
    Integer y =
        parseJOptionInput(
            JOptionPane.showInputDialog("What # do you want to find the multiple for? "),
            Integer.class);

    if (instances == null || y == null) {
      JOptionPane.showMessageDialog(null, "Invalid input");
      return;
    }
    System.out.println(generateMultiples(instances, y));
  }

  private String generateMultiples(int count, int multiple) {
    count++;
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      numbers.add(i * multiple);
    }
    return arrayToString(
        numbers); // todo fix the output thing not working across the 20 different types of stuipd
    // things i have
  }
}
