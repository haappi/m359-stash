package io.github.haappi.fivechallenges;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static io.github.haappi.fivechallenges.Common.loadFMXLView;

public class AreaOfACircle {
  public AreaOfACircle() {
    // Usually I would make it private, but then FXML is going to be a problem
  }

  @FXML private Text output;
  @FXML private TextField textField;

  public static void run(Stage stage) {
    loadFMXLView(stage, "area-of-a-circle-view.fxml");
  }

  @FXML
  protected void onButtonSlap() {
    output.setTextAlignment(TextAlignment.CENTER); // IT WONT CENTERRRR
    if (textField.getText().equals("")) {
      output.setText("Please enter a radius");
      output.setFill(javafx.scene.paint.Color.RED);
    } else {
      try {
        double radius = Double.parseDouble(textField.getText());
        output.setText(
            String.format(
                "The area of a circle with radius %.2f is %.2f", radius, getArea(radius)));
        output.setFill(javafx.scene.paint.Color.GREEN);
      } catch (NumberFormatException e) {
        output.setText("Please enter a valid number");
        output.setFill(javafx.scene.paint.Color.RED);
      }
    }
  }

  public static double getArea(double radius) {
    return Math.PI * Math.pow(radius, 2);
  }
}
