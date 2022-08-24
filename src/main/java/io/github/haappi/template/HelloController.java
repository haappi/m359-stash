package io.github.haappi.template;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HelloController {
  @FXML protected TextField inputA;
  @FXML protected TextField inputB;
  @FXML protected Text output;

  @FXML
  protected void onButtonSlap() {
    if (!areValidInputs()) {
      output.setText("Please enter a number for A & B.");
      output.setFill(Color.RED);
      return;
    }
    int integerAnswer = getInteger(inputA) / getInteger(inputB);
    int remainder = getInteger(inputA) % getInteger(inputB);

    output.setFill(Color.GREEN);
    output.setText(String.format("%d with a remainder of %d", integerAnswer, remainder));
  }

  protected String getString(TextField field) {
    return field.getText();
  }

  protected Integer getInteger(TextField field) {
    return getInteger(getString(field));
  }

  protected Integer getInteger(String string) {
    return Integer.parseInt(string);
  }

  protected boolean areValidInputs() {
    if (getString(inputA).isEmpty() || getString(inputB).isEmpty()) {
      return false;
    }
    try {
      Integer.parseInt(getString(inputA));
      Integer.parseInt(getString(inputB));
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
}
