package io.github.haappi;

import java.util.HashMap;

public class Utils {
    public static String getHashMapAsJsonString(HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String key : map.keySet()) {
            sb.append("\"").append(key).append("\":\"").append(map.get(key)).append("\",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }
}
