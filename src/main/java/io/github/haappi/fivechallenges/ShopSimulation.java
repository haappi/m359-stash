package io.github.haappi.fivechallenges;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static io.github.haappi.fivechallenges.Common.loadFMXLView;

public class ShopSimulation {
    @FXML
    protected Text subTotal;

    public static void run(Stage stage) {
        loadFMXLView(stage, "shop-simulation.fxml");
    }
    private double total = 0;
    private double tax = 0.06;

    @FXML
    protected void cookies() {
        total += 1.50;
        updateTotal();
    }

    @FXML
    protected void checkout() {
        updateTotal();
    }

    @FXML
    protected void milk() {
        total += 2.50;
        updateTotal();
    }

    @FXML
    protected void cream() {
        total += 3.50;
        updateTotal();
    }

    private void updateTotal() {
        subTotal.setText("Subtotal: " + format(total) + "\nTax: " + format((total * tax)) + "\nTotal: " + format((total + (total * tax))));
    }

    private double format(double d) {
        return Math.round(d * 100.0) / 100.0;
    }
}
