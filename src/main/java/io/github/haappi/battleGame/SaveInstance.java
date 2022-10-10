package io.github.haappi.battleGame;

public class SaveInstance {
    private final String name;
    private final String description;
    private final String path;
    private final Long lastModified;

    public SaveInstance(String name, String description, String path, Long lastModified) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.lastModified = lastModified;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public String getLastModifiedString() {
        return String.format("%tA %tB %td %tY", lastModified, lastModified, lastModified, lastModified);
    }

    @Override
    public String toString() {
        return "SaveInstance{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", lastModified=" + lastModified +
                '}';
    }
}
