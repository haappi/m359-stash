package io.github.haappi.packets;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Card implements Packet {
//    private final String fileURI;
//
//    private final Enums size;
//    private final Enums color;
//    private final Enums container;
//    private final Enums pattern;
//
//    private final boolean isBackCard;

    private static final ConcurrentHashMap<String, Image> imageHashMap = new ConcurrentHashMap<>();

    public static Image getImage(String fileUri) {
        String[] splitted = fileUri.split("/");
        final String fileName = splitted[splitted.length - 1];
        if (imageHashMap.get(fileName) != null) {
            return imageHashMap.get(fileName);
        }
        Image image = new Image(fileUri, true);
        imageHashMap.put(fileName, image);
        return image;
    }
}
