package io.github.haappi.BoardGame;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static JedisPool initJedis(HashMap<String, String> config) {
        return new JedisPool(
                String.format(
                        "redis://%s:%s@%s:%s",
                        config.get("USER"),
                        config.get("PASS"),
                        config.get("IP"),
                        config.get("PORT")));
    }

    public static HashMap<String, String> loadConfig() throws IOException {
        createFile("config.txt");
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("config.txt")));
        HashMap<String, String> config = new HashMap<>();
        for (String line : lines) {
            String[] split = line.split("=");
            config.put(split[0], split[1]);
        }
        return config;
    }

    public static void createFile(String path) throws IOException {
        Path path1 = Paths.get(path);
        if (!Files.exists(path1)) {
            Files.createFile(path1);
        }
    }

    public static JedisPubSub getListener() {
        return new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("received " + message + " from " + channel);
                Test clazz = (getObject(message));
                System.out.println(clazz.getName());
                System.out.println(HelloApplication.getInstance().getGson().fromJson(message, Test.class));
            }
        };
    }

    /**
     * Attempts to cast a given {@link String} automatically into its {@link Class<T>}
     * May return <b><font color ="orange">null</font></b> if the {@link ClassTypes} isn't found.
     * @param json The {@link String} json of the given object.
     * @return <b><font color ="orange">null</font></b> or the {@link ClassTypes} casted properly.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObject(String json) {
        Map<Object, Object> map = HelloApplication.getInstance().getGson().fromJson(json, Map.class);

        switch (ClassTypes.valueOf((String) map.get("classType"))) {
            case TEST -> {
                return (T) castType(json, Test.class);
            }
        }
        return null;

    }

    public static <T> T castType(String json, Class<T> tClass) {
        return HelloApplication.getInstance().getGson().fromJson(json, tClass);
    }

}
