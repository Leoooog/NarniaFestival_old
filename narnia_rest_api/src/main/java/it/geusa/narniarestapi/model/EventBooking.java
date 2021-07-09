package it.geusa.narniarestapi.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.bson.Document;
import org.bson.types.ObjectId;

@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EventBooking implements MongoObject {

    @EqualsAndHashCode.Include
    ObjectId id;
    ObjectId eventId;
    ObjectId userId;
    Long timestamp;

    public EventBooking(Document doc) {
        this.id = doc.getObjectId("_id");
        this.eventId = doc.getObjectId("event_id");
        this.userId = doc.getObjectId("user_id");
        this.timestamp = doc.getLong("timestamp");
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("event_id", eventId);
        doc.append("user_id", userId);
        doc.append("timestamp", timestamp);
        return doc;
    }

}
