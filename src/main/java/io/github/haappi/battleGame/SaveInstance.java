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
        return String.format("%tA %<tB %<td %<tY", lastModified);
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Formatter.html#syntax
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Formatter.html#dt
        // String.format can format timestamps. The 't' is to specify that it's a timestamp. The 'A' is stolen from the
        // JavaDocs for "Locale-specific full name of the day of the week, e.g. "Sunday", "Monday"".
        // The '<' is to prevent me from having to pass 4 parameters to String.format. It basically uses the previous parameter for formatting.
    }

    @Override
    public String toString() {
        return "SaveInstance{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", lastModified=" + getLastModifiedString() +
                '}';
    }
}
