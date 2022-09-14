package io.github.haappi.psuedoCode;

import javafx.fxml.FXML;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.psuedoCode.Common.arrayToString;
import static io.github.haappi.psuedoCode.Common.parseJOptionInput;

public class HelloController {


  private String generateFibonacci(int count) {
    List<Integer> numbers = new ArrayList<>();
    numbers.add(0);
    numbers.add(1);
    for (int i = 2; i < count; i++) {
      numbers.add(numbers.get(i - 2) + numbers.get(i - 1)); // add the last two numbers together
    }
    return arrayToString(numbers);
  }

  @FXML
  protected void doFibonacci() {
    Integer inputDialog = parseJOptionInput(JOptionPane.showInputDialog("How many things "), 1);
    System.out.println(generateFibonacci(inputDialog));
  }
}
