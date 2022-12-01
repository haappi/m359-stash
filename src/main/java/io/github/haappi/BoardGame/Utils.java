package io.github.haappi.BoardGame;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {

    public static JedisPool initJedis(HashMap<String, String> config) {
        if (!config.containsKey("USER")
                || !config.containsKey("PASS")
                || !config.containsKey("IP")
                || !config.containsKey("PORT")) {
            return new JedisPool();
        }
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
            byte[] bytesData = ("CLIENT-ID=" + UUID.randomUUID()).getBytes();
            Files.write(path1, bytesData);
        }
    }

    public static JedisPubSub getListener() {
        return new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("received " + message + " from " + channel);
                Test clazz = (getObject(message));
                System.out.println(clazz.getName());
            }
        };
    }

    /**
     * Attempts to cast a given {@link String} automatically into its {@link Class<T>}
     * <br>May return <b><font color ="orange">null</font></b> if the {@link ClassTypes} isn't found.
     *
     * @param json The {@link String} json of the given object.
     * @return <b><font color ="orange">null</font></b> or the {@link ClassTypes} casted properly.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObject(String json) {
        Map<Object, Object> map =
                HelloApplication.getInstance().getGson().fromJson(json, Map.class);
        ClassTypes constant;
        try {
            constant = ClassTypes.valueOf((String) map.get("classType"));
        } catch (IllegalArgumentException e) {
            return null;
        }
        switch (constant) {
            case TEST:
                return (T) HelloApplication.getInstance().getGson().fromJson(json, Test.class);
            default:
                return null;
        }
    }

    public static <T> T castType(String json, Class<T> tClass) {
        return HelloApplication.getInstance().getGson().fromJson(json, tClass);
    }

    public static void p(Jedis instance, String channel, String message) {
        String newMessage = message.substring(0, message.length() - 1);
        newMessage =
                newMessage
                        + ",\"clientID\":\""
                        + HelloApplication.getInstance().getClientID()
                        + "\"}";
        // ,"clientID":"...";
        instance.publish(channel, newMessage);
    }
}
