package it.geusa.narniarestapi.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.bson.Document;


@EqualsAndHashCode(callSuper = true)
@Value
public class ReservationNeededNarniaEvent extends NarniaEvent {

    int maxSeats;
    int occupiedSeats;

    public ReservationNeededNarniaEvent(Document doc) {
        super(doc);
        this.maxSeats = doc.getInteger("max_seats");
        this.occupiedSeats = doc.getInteger("occupied_seats");
    }

    @Override
    public Document toDocument() {
        Document doc = super.toDocument();
        doc.append("max_seats", maxSeats);
        doc.append("occupied_seats", occupiedSeats);
        return doc;
    }
}
