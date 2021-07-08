package it.geusa.narniarestapi.models;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.UUID;

public class FoodStamp implements MongoObject {
    private final ObjectId id;
    private final int value;
    private final boolean valid;
    private final UUID uuid;

    public FoodStamp(Document doc) {
        this.id = doc.getObjectId("_id");
        this.value = doc.getInteger("value");
        this.valid = doc.getBoolean("valid");
        this.uuid = doc.get("uuid", UUID.class);
    }


    public FoodStamp(ObjectId id, int value, boolean valid, UUID uuid) {
        this.id = id;
        this.value = value;
        this.valid = valid;
        this.uuid = uuid;
    }

    public ObjectId getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public boolean isValid() {
        return valid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var that = (FoodStamp) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public Document toDocument() {
        return null;
    }
}
