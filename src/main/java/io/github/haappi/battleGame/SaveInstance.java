package io.github.haappi.battleGame;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SaveInstance {

    private final String path;
    private final Long lastModified;
    private Map<String, String> information = new HashMap<>();

    public SaveInstance(String data, String path, Long lastModified) {
        this.path = path;
        this.lastModified = lastModified;

        this.information = (Map<String, String>) Utils.getMapFromString(Utils.getSaveInformation(data, lastModified, HelloApplication.getInstance().getGsonInstance()), lastModified, new Gson());
    }

    public SaveInstance() {
        this.path = null;
        this.lastModified = 0L;
    }

    public static String getLastModifiedString(Long lastModified) {
        return String.format("%tA %<tB %<td, %<tY. %<tI:%<tM:%<tS %<tp", lastModified);
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Formatter.html#syntax
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Formatter.html#dt
        // String.format can format timestamps. The 't' is to specify that it's a timestamp. The 'A' is stolen from the
        // JavaDocs for "Locale-specific full name of the day of the week, e.g. "Sunday", "Monday"".
        // The '<' is to prevent me from having to pass 4 parameters to String.format. It basically uses the previous parameter for formatting.
    }

    public String getPath() {
        return path;
    }

    public Long getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return information.getOrDefault("name", "Empty");
    }

    public String getDetails() {
        return String.format("Name: %s\n" +
                "Description: %s\n" +
                "File Path: %s\n" +
                "Last Modified: %s", information.getOrDefault("name", "Empty"), information.getOrDefault("description", "Empty"), this.path != null ? this.path : "new-save-file", SaveInstance.getLastModifiedString(this.lastModified));
    }
}
