package it.geusa.narniarestapi.models;

import org.bson.Document;
import org.bson.types.ObjectId;

public class EventBooking implements MongoObject {

    private final ObjectId id;
    private final ObjectId eventId;
    private final Long timestamp;

    public EventBooking(Document doc) {
        this.id = doc.getObjectId("_id");
        this.eventId = doc.getObjectId("event_id");
        this.timestamp = doc.getLong("timestamp");
    }

    public EventBooking(ObjectId id, ObjectId eventId, Long timestamp) {
        this.id = id;
        this.eventId = eventId;
        this.timestamp = timestamp;
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getEventId() {
        return eventId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var that = (EventBooking) o;

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
