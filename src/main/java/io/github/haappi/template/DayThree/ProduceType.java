package io.github.haappi.template.DayThree;

public class ProduceType {
    private final String name;
    private int count = 0;

    public ProduceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProduceType{\n" +
                "name=" + this.name + "\n" +
                "count=" + this.count + "}";
    }
}
