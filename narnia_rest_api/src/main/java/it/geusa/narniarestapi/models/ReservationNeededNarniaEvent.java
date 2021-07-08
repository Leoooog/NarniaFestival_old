package it.geusa.narniarestapi.models;

import it.geusa.narniarestapi.models.enums.EventType;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

public class ReservationNeededNarniaEvent extends NarniaEvent {

    private final int maxSeats;
    private final int occupiedSeats;

    public ReservationNeededNarniaEvent(Document doc) {
        super(doc);
        this.maxSeats = doc.getInteger("max_seats");
        this.occupiedSeats = doc.getInteger("occupied_seats");
    }

    public ReservationNeededNarniaEvent(ObjectId id, String title, String subtitle, String description, String place, Date date, double price, boolean reservationNeeded, EventType eventType, String address, double duration, int maxSeats, int occupiedSeats) {
        super(id, title, subtitle, description, place, date, price, reservationNeeded, eventType, address, duration);
        this.maxSeats = maxSeats;
        this.occupiedSeats = occupiedSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }
}
