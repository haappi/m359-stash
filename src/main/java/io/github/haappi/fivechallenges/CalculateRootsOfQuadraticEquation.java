package io.github.haappi.fivechallenges;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static io.github.haappi.fivechallenges.Common.loadFMXLView;
import static io.github.haappi.fivechallenges.ShopSimulation.format;

public class CalculateRootsOfQuadraticEquation {

    @FXML
    protected Text output;
    @FXML
    protected TextField c;
    @FXML
    protected TextField b;
    @FXML
    protected TextField a;

    public static void run(Stage stage) {
        loadFMXLView(stage, "calculate-roots.fxml");
    }

    @FXML
    protected void find() {
        if (!checkIfValuesExist()) {
            output.setText("Please enter doubles for a, b and c");
            output.setFill(javafx.scene.paint.Color.RED);
        } else {
            output.setFill(javafx.scene.paint.Color.GREEN);
            double aValue = Double.parseDouble(a.getText());
            double bValue = Double.parseDouble(b.getText());
            double cValue = Double.parseDouble(c.getText());
            double discriminant = getDiscriminant(aValue, bValue, cValue);

            if (discriminant < 0) {
                output.setText("No real roots");
            } else if (discriminant == 0) {
                output.setText("1 real root: 0");
            } else {
                output.setText("2 real roots: " + format(getRoot1(aValue, bValue, cValue)) + " and " + format(getRoot2(aValue, bValue, cValue)));
            }
        }
    }

    private double getDiscriminant(double aValue, double bValue, double cValue) {
        return Math.pow(bValue, 2) - 4 * aValue * cValue;
        // aValue * x^2 + bValue * x + cValue = 0
    }

    private double getRoot1(double aValue, double bValue, double cValue) {
        return (-bValue + Math.sqrt(getDiscriminant(aValue, bValue, cValue))) / (2 * aValue);
    }

    private double getRoot2(double aValue, double bValue, double cValue) {
        return (-bValue - Math.sqrt(getDiscriminant(aValue, bValue, cValue))) / (2 * aValue);
    }

    /**
     * Checks if the values are valid
     * @return true if the values are valid, and not blank
     */
    private boolean checkIfValuesExist() {
        if (c.getText().equals("") || b.getText().equals("") || a.getText().equals("")) {
            return false;
        }
        try {
            Double.parseDouble(c.getText());
            Double.parseDouble(b.getText());
            Double.parseDouble(a.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
