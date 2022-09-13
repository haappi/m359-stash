package io.github.haappi.psuedoCode;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
  @FXML private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
    System.out.println(generateFibonacci(6));
  }

  private String generateFibonacci(int count) {
    int a = 1;
    int b = 1;

    String output = "";
    for (int i = 0; i < count; i++) {
      output = doSomething(output, a, b, a + b);
      int temp = b;
      b = a + b;
      a = temp;
    }
    return output;
  }

  private String doSomething(String string, int a, int b, int c) {
    string = string + a + ", " + b + ", " + c + ", ";
    return string;
  }
}
