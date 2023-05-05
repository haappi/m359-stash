package io.github.haappi.packets;

import java.io.Serializable;

public interface Packet extends Serializable {
    /**
     * Handles the incoming packet.<br>
     * If you wish to ignore the packet (one-way packets), override the method with an empty body.
     */
    default void handle() {
        throw new RuntimeException(
                "Attempted to handle a.txt packet, but method isn't overridden in Child class.");
    }
}
