package io.github.haappi;

import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;

import java.util.Arrays;
import java.util.List;

public enum Swatching {
    AMBER(Swatch.AMBER),
    BLUE(Swatch.BLUE),
    BLUE_GREY(Swatch.BLUE_GREY),
    BROWN(Swatch.BROWN),
    CYAN(Swatch.CYAN),
    DEEP_ORANGE(Swatch.DEEP_ORANGE),
    DEEP_PURPLE(Swatch.DEEP_PURPLE),
    GREEN(Swatch.GREEN),
    GREY(Swatch.GREY),
    INDIGO(Swatch.INDIGO),
    LIGHT_BLUE(Swatch.LIGHT_BLUE),
    LIGHT_GREEN(Swatch.LIGHT_GREEN),
    LIME(Swatch.LIME),
    ORANGE(Swatch.ORANGE),
    PINK(Swatch.PINK),
    PURPLE(Swatch.PURPLE),
    RED(Swatch.RED),
    TEAL(Swatch.TEAL),
    YELLOW(Swatch.YELLOW);


    private final Swatch swatch;

    Swatching(Swatch swatch) {
        this.swatch = swatch;
    }

    public static Swatching[] getAll() {
        return Swatching.values();
    }

    public static List<Swatching> getAllAsList() {
        return Arrays.asList(Swatching.values());
    }

    @Override
    public String toString() {
        return swatch.name();
    }

    public Swatch getSwatch() {
        return swatch;
    }

    public void assignTo(Scene scene) {
        swatch.assignTo(scene);
    }
}
