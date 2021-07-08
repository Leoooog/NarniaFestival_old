package it.geusa.narniarestapi.model;

import it.geusa.narniarestapi.model.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NarniaEvent implements MongoObject {

    @EqualsAndHashCode.Include
    ObjectId id;
    String title;
    String subtitle;
    String description;
    String place;
    Date date;
    double price;
    boolean reservationNeeded;
    EventType eventType;
    String address;
    double duration;

    public NarniaEvent(Document doc) {
        this.id = doc.getObjectId("_id");
        this.title = doc.getString("title");
        this.subtitle = doc.getString("subtitle");
        this.description = doc.getString("description");
        this.place = doc.getString("place");
        this.date = doc.getDate("date");
        this.price = doc.getDouble("price");
        this.reservationNeeded = doc.getBoolean("reservation_needed");
        this.eventType = EventType.valueOf(doc.getString("event_type"));
        this.address = doc.getString("address");
        this.duration = doc.getDouble("duration");
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("title", title);
        doc.append("subtitle", subtitle);
        doc.append("description", description);
        doc.append("place", place);
        doc.append("date", date);
        doc.append("price", price);
        doc.append("reservation_needed", reservationNeeded);
        doc.append("event_type", eventType);
        doc.append("address", address);
        doc.append("duration", duration);
        return doc;
    }
}
