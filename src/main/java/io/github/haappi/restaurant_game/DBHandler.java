package io.github.haappi.restaurant_game;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DBHandler {
    private static DBHandler instance;

    private DBHandler() throws IOException {
        ConfigFile configFile =
                HelloApplication.gson.fromJson(
                        new String(Files.readAllBytes(Paths.get("src/main/resources/config.json"))),
                        ConfigFile.class);

        ConnectionString connectionString = new ConnectionString(configFile.getConnection());
        MongoClientSettings settings =
                MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                        .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("test");
    }

    public static synchronized DBHandler getInstance() throws IOException {
        if (DBHandler.instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }
}
