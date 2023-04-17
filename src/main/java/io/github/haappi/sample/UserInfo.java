package io.github.haappi.sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserInfo {

    private final StringProperty favColor = new SimpleStringProperty(this, "favColor", "#000000");

    public String getFavColor() {
        return favColor.get();
    }

    public StringProperty favColorProperty() {
        return favColor;
    }

    public void setFavColor(String favColor) {
        this.favColor.set(favColor);
    }
}
