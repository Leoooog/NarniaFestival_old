package it.geusa.narniarestapi.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.UUID;

@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FoodStamp implements MongoObject {
    @EqualsAndHashCode.Include
    ObjectId id;
    ObjectId ownerId;
    int value;
    boolean valid;
    UUID uuid;

    public FoodStamp(Document doc) {
        this.id = doc.getObjectId("_id");
        this.ownerId = doc.getObjectId("owner_id");
        this.value = doc.getInteger("value");
        this.valid = doc.getBoolean("valid");
        this.uuid = doc.get("uuid", UUID.class);
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("owner_id", ownerId);
        doc.append("value", value);
        doc.append("valid", valid);
        doc.append("uuid", uuid);
        return doc;
    }
}
