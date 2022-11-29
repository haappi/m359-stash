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

public class Utils {

    public static JedisPool initJedis(HashMap<String, String> config) {
        return new JedisPool(String.format("redis://%s:%s@%s:%s", config.get("USER"), config.get("PASS"), config.get("IP"), config.get("PORT")));
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
            // https://codedestine.com/redis-jedis-pub-sub-java/
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Channel " + channel + " has sent a message : " + message );
                if(channel.equals("C1")) {
                    unsubscribe(channel);
                }
            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("Client is Subscribed to channel : "+ channel);
                System.out.println("Client is Subscribed to "+ subscribedChannels + " no. of channels");
            }

            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println("Client is Unsubscribed from channel : "+ channel);
                System.out.println("Client is Subscribed to "+ subscribedChannels + " no. of channels");
            }

        };
    }
}
