package io.github.haappi.psuedoCode;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.psuedoCode.Common.arrayToString;

public class HelloController {
  @FXML
  private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    System.out.println(generateFibonacci());
  }


  private String generateFibonacci() {
    List<Integer> numbers = new ArrayList<>();
    numbers.add(0);
    numbers.add(1);
    for (int i = 2; i < 10; i++) {
      numbers.add(numbers.get(i - 2) + numbers.get(i - 1)); // add the last two numbers together
    }
    return arrayToString(numbers);
  }

}
