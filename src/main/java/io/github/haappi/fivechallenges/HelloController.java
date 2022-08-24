package io.github.haappi.fivechallenges;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HelloController {
	@FXML
	protected Text output;
	@FXML
	protected TextField number1;
	@FXML
	protected TextField number2;


	@FXML
	protected void onButtonSlap() {
        if (!isValidInputs()) {
            return;
        }
        boolean isMultipleOrNot = isMultiple(toString(number1), toString(number2));

        String message = isMultipleOrNot ? toString(number1) + " is a multiple of " + toString(number2) : toString(number1) + " is not a multiple of " + toString(number2);
        output.setText(message);
        output.setFill(isMultipleOrNot ? Color.GREEN : Color.RED);
	}

    protected int toString(TextField textField) {
        return Integer.parseInt(textField.getText());
    }

    protected boolean isValidInputs() {
        if (number1.getText().isEmpty() || number2.getText().isEmpty()) {
            output.setText("Please enter two numbers");
            output.setFill(Color.RED);
            return false;
        }
        try {
            Integer.parseInt(number1.getText());
            Integer.parseInt(number2.getText());
        } catch (NumberFormatException e) {
            output.setText("Please enter two numbers");
            output.setFill(Color.RED);
            return false;
        }
        output.setFill(Color.GREEN);
        return true;
    }

    protected boolean isMultiple(int number1, int number2) {
        return number1 % number2 == 0;
    }


}
