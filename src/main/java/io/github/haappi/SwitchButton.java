package io.github.haappi;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

public class SwitchButton extends GridPane {
    // https://stackoverflow.com/a/75921717

    private final Circle knob;
    private final Label lblDesc;

    private BooleanProperty isOn = new SimpleBooleanProperty(false);

    private String trueValue;
    private String falseValue;

    public SwitchButton(String trueValue, String falseValue, boolean defaultState) {
        this();
        this.trueValue = trueValue;
        this.falseValue = falseValue;
        isOn.set(defaultState);

        paintSwitch();
    }

    public SwitchButton(String trueValue, String falseValue) {
        this();
        this.trueValue = trueValue;
        this.falseValue = falseValue;
        isOn.set(false);

        paintSwitch();
    }

    public SwitchButton() {
        this.trueValue = "On";
        this.falseValue = "Off";
        // Sizing
        ColumnConstraints colThird = new ColumnConstraints();
        colThird.setPercentWidth(100d / 3d);
        getColumnConstraints().addAll(colThird, colThird, colThird);
        setMaxHeight(25);
        setMaxWidth(90);
        setMinHeight(25);
        setMinWidth(90);

        // Property
        isOn = new SimpleBooleanProperty(false);

        // Knob
        knob = new Circle(12.5);
        knob.setStyle("-fx-border-color: #ABABAB;");
        knob.setFill(Color.GRAY);

        // Description Label
        lblDesc = new Label("Off");
        lblDesc.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(lblDesc, HPos.CENTER);

        // Click
        setOnMouseClicked(e -> {
            isOn.set(!isOn.get());
            paintSwitch();
        });

        paintSwitch();
    }

    /**
     * Adds a listener for when the toggle changes
     *
     * @param listener
     */
    public void addListener(ChangeListener<Boolean> listener) {
        isOn.addListener(listener);
    }

    /**
     * Draws the switch
     */
    private void paintSwitch() {
        getChildren().clear();

        if (isOn.get()) {
            add(knob, 2, 0);
            lblDesc.setText(trueValue);
            add(lblDesc, 0, 0, 2, 1);
            GridPane.setHalignment(lblDesc, HPos.CENTER);
            setStyle("-fx-background-radius: 30; -fx-background-color: #b0dea0; -fx-border-radius: 30;-fx-border-width:2; -fx-border-color: #ABABAB;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 1);");
        } else {
            add(knob, 0, 0);
            lblDesc.setText(falseValue);
            add(lblDesc, 1, 0);
            setStyle("-fx-background-radius: 30; -fx-background-color: #D6D6D6; -fx-border-radius: 30;-fx-border-width:2; -fx-border-color: #ABABAB;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 1);");
        }
    }

    /**
     * Sets the switch value
     *
     * @param value
     */
    public void setValue(boolean value) {
        isOn.set(value);
        paintSwitch();
    }

}