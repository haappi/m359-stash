package io.github.haappi.shared;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class Image extends javafx.scene.image.Image implements Packet {
    public Image(String url) {
        super(url);
    }

    public Image(String url, boolean backgroundLoading) {
        super(url, backgroundLoading);
    }

    public Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
    }

    public Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);
    }

    public Image(InputStream is) {
        super(is);
    }

}
