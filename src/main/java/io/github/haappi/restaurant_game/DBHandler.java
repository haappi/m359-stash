package io.github.haappi.restaurant_game;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.BsonValue;
import org.bson.Document;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DBHandler {
    private static DBHandler instance;
    private final MongoClient client;

    public static final String dbName = "haappi";
    public static final String collectionName = "restaurantGame";

    private DBHandler() throws IOException {
        ConfigFile configFile =
                HelloApplication.gson.fromJson(
                        new String(Files.readAllBytes(Paths.get("src/main/resources/config.json"))),
                        ConfigFile.class);
        System.out.println(configFile.getConnection());

        ConnectionString connectionString = new ConnectionString(configFile.getConnection());
        MongoClientSettings settings =
                MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                        .build();
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

    public Document insert(Object tclass, MongoCollection<Document> collection) {
        //        BsonValue id = collection.insertOne(new Document("aaaa",
        // HelloApplication.gson.toJson(tclass))).getInsertedId();
        System.out.println(Document.parse(HelloApplication.gson.toJson(tclass)));
        BsonValue id =
                collection
                        .insertOne(Document.parse(HelloApplication.gson.toJson(tclass)))
                        .getInsertedId();
        return collection.find(new Document("_id", id)).first();
    }

    /**
     * Attempts to find a document with the provided query. This will search in the default DB/Collection as specified in this class.
     * @param document {@link Document} a document to look for.
     * @return A {@link Document} or null if not found.
     */
    public @Nullable Document findDocument(Document document) {
        return getCollection(dbName, collectionName).find(document).first();
    }

    /**
     * Attempts to find a document with the given _id. This will search in the default DB/Collection as specified in this class.
     * @param _id A {@link String} containing the _id to search for
     * @return A {@link Document} if found, else null.
     */
    public @Nullable Document findDocument(String _id) {
        return findDocument(new Document("_id", _id));
    }
}
