package io.github.haappi.shared;

import javafx.scene.image.Image;

import java.util.concurrent.ConcurrentHashMap;

public class Utils {
    public static final ConcurrentHashMap<String, Image> imageHashMap = new ConcurrentHashMap<>();
    public static String charAt(String string, int position) {
        return String.valueOf(string.charAt(position));
    }
}
