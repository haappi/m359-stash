package io.github.haappi.packets;

import java.io.Serializable;

public interface Packet extends Serializable {
    void handle();
}
