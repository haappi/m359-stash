package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;
import com.mongodb.client.model.Filters;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class CustomClass {

    @Expose
    private final String _id;

    public CustomClass(String _id) {
        this._id = _id;
    }

    public CustomClass() {
        this._id = new ObjectId().toString();
    }

    public String get_id() {
        return _id;
    }

    public Bson getFilter() {
        return Filters.eq("_id", this._id);
    }
}
