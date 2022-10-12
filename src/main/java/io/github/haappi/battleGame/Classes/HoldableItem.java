package io.github.haappi.battleGame.Classes;

/**
 * @author haappi
 * Base class that represents an item that can be held by a player
 */
public class HoldableItem {
    private String name;
    private int count;

    public HoldableItem(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void removeCount(int count) {
        this.count -= count;
    }

    public void removeCount() {
        this.count--;
    }

    public void addCount() {
        this.count++;
    }

    public String toString() {
        return "HoldableItem{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }


}
