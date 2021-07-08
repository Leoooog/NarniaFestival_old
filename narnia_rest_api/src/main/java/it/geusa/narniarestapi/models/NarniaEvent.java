package it.geusa.narniarestapi.models;

import it.geusa.narniarestapi.models.enums.EventType;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

public class NarniaEvent implements MongoObject {

    private final ObjectId id;
    private final String title;
    private final String subtitle;
    private final String description;
    private final String place;
    private final Date date;
    private final double price;
    private final boolean reservationNeeded;
    private final EventType eventType;
    private final String address;
    private final double duration;

    public NarniaEvent(Document doc) {
        this.id = doc.getObjectId("_id");
        this.title = doc.getString("title");
        this.subtitle = doc.getString("subtitle");
        this.description = doc.getString("description");
        this.place = doc.getString("place");
        this.date = doc.getDate("date");
        this.price = doc.getDouble("price");
        this.eventType = EventType.valueOf(doc.getString("event_type"));
        this.reservationNeeded = doc.getBoolean("reservation_needed");
        this.address = doc.getString("address");
        this.duration = doc.getDouble("duration");
    }

    public NarniaEvent(ObjectId id, String title, String subtitle, String description, String place, Date date, double price, boolean reservationNeeded, EventType eventType, String address, double duration) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.place = place;
        this.date = date;
        this.price = price;
        this.reservationNeeded = reservationNeeded;
        this.eventType = eventType;
        this.address = address;
        this.duration = duration;
    }

    public ObjectId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBookingNeeded() {
        return reservationNeeded;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getAddress() {
        return address;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var event = (NarniaEvent) o;

        return id.equals(event.id);
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
