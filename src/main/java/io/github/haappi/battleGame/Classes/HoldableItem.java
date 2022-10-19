package io.github.haappi.battleGame.Classes;

import io.github.haappi.battleGame.InventoryItem;
import org.jetbrains.annotations.Nullable;

/**
 * @author haappi
 * Base class that represents an item that can be held by a player
 */
public class HoldableItem extends InventoryItem {
    private String name;
    private final Double price;

    public HoldableItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Nullable Double getPrice() {
        return this.price;
    }

    public String toString() {
        return "HoldableItem{" +
                "name='" + name + '\'' +
                ", price=" + this.price +
                '}';
    }


}
