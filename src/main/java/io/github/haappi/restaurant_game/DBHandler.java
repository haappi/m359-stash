package io.github.haappi.restaurant_game;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DBHandler {
    public static final String dbName = "haappi";
    public static final String collectionName = "restaurantGame";
    private static DBHandler instance;
    private final MongoClient client;

    private DBHandler() throws IOException {
        ConfigFile configFile =
                HelloApplication.gson.fromJson(
                        new String(Files.readAllBytes(Paths.get("src/main/resources/config.json"))),
                        ConfigFile.class);
        ConnectionString connectionString = new ConnectionString(configFile.getConnection());
        MongoClientSettings settings =
                MongoClientSettings.builder().applyConnectionString(connectionString).build();
        client = MongoClients.create(settings);
    }

    public static synchronized DBHandler getInstance() throws IOException {
        if (DBHandler.instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase(String name) {
        return client.getDatabase(name);
    }

    public MongoCollection<Document> getCollection(MongoDatabase dbName, String colName) {
        return dbName.getCollection(colName);
    }

    public MongoCollection<Document> getCollection(String dbName, String colName) {
        return this.getDatabase(dbName).getCollection(colName);
    }

    public <T> T getClassFromDocument(Document document, Class<T> tClass) {
        return HelloApplication.gson.fromJson(document.toJson(), tClass);
    }

    /**
     * Attempts to upsert a {@link CustomClass} type into the specified {@link MongoCollection}.
     *
     * @param tClass     A {@link CustomClass} object to insert.
     * @param collection The {@link MongoCollection} to insert this into.
     * @return A {@link Document}, if it upserted properly, else null.
     */
    public @Nullable Document insert(
            @NotNull CustomClass tClass, @Nullable MongoCollection<Document> collection) {
        if (collection == null) {
            collection = getCollection(dbName, collectionName);
        }
        collection.updateOne(
                tClass.getFilter(),
                new Document("$set", Document.parse(HelloApplication.gson.toJson(tClass))),
                new UpdateOptions().upsert(true));

        return collection.find(new Document("_id", tClass.get_id())).first();
    }

    /**
     * Attempts to upsert a {@link CustomClass} type into the default {@link MongoCollection} as specified in this class.
     * @param tClass A {@link CustomClass} object to insert.
     * @return A {@link Document}, if it upserted properly, else null.
     */
    public @Nullable Document insert(CustomClass tClass) {
        return this.insert(tClass, getCollection(dbName, collectionName));
    }

    /**
     * Attempts to find a document with the provided query. This will search in the default DB/Collection as specified in this class.
     *
     * @param document {@link Document} a document to look for.
     * @return A {@link Document} or null if not found.
     */
    public @Nullable Document findDocument(@NotNull Document document) {
        return getCollection(dbName, collectionName).find(document).first();
    }

    /**
     * Attempts to find a document with the given _id. This will search in the default DB/Collection as specified in this class.
     *
     * @param _id A {@link String} containing the _id to search for
     * @return A {@link Document} if found, else null.
     */
    public @Nullable Document findDocument(@NotNull String _id) {
        return findDocument(new Document("_id", _id));
    }
}
