package io.github.haappi.template;

import static io.github.haappi.template.Utils.getRandomNumber;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML private Label welcomeText;

    private Button[][] buttons;
    private Objects[][] objects;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        buttons = new Button[5][5];
        objects = new Objects[5][5];

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                Button label = new Button("*");
                buttons[i][j] = label;

                objects[i][j] = new Grass(i, j);
            }
        }
        int i = getRandomNumber(0, 4);
        int j = getRandomNumber(0, 4);
        objects[i][j] = new Elephant(i, j);

        welcomeText.setText(objects[i][j] + "");
    }
}
