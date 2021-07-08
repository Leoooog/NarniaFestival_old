package it.geusa.narniarestapi.model;

import it.geusa.narniarestapi.model.enums.ProfileType;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements MongoObject {
    @EqualsAndHashCode.Include
    ObjectId id;
    String username;
    String email;
    String firstName;
    String lastName;
    boolean emailConfirmed;
    boolean enabled;
    ProfileType profileType;
    List<FoodStamp> foodStamps;
    List<EventBooking> eventBookings;

    public User(Document doc) {
        this.id = doc.getObjectId("_id");
        this.username = doc.getString("username");
        this.email = doc.getString("email");
        this.firstName = doc.getString("first_name");
        this.lastName = doc.getString("last_name");
        this.emailConfirmed = doc.getBoolean("email_confirmed");
        this.enabled = doc.getBoolean("enabled");
        this.profileType = ProfileType.valueOf(doc.getString("profile_type"));
        this.foodStamps = parseFoodStamps(doc);
        this.eventBookings = parseEventBookings(doc);
    }

    private List<EventBooking> parseEventBookings(Document doc) {
        List<EventBooking> eventBookings = new ArrayList<>();
        var eventBookingsDoc = doc.getList("event_bookings", Document.class);
        for (Document ebDoc : eventBookingsDoc) {
            EventBooking e = new EventBooking(ebDoc);
            eventBookings.add(e);
        }
        return eventBookings;
    }

    private List<FoodStamp> parseFoodStamps(Document doc) {
        List<FoodStamp> foodStamps = new ArrayList<>();
        var foodStampsDoc = doc.getList("food_stamps", Document.class);
        for (Document ebDoc : foodStampsDoc) {
            FoodStamp e = new FoodStamp(ebDoc);
            foodStamps.add(e);
        }
        return foodStamps;
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("username", username);
        doc.append("email", email);
        doc.append("first_name", firstName);
        doc.append("last_name", lastName);
        doc.append("email_confirmed", emailConfirmed);
        doc.append("enabled", enabled);
        doc.append("profile_type", profileType);
        doc.append("food_stamps", foodStamps);
        doc.append("event_bookings", eventBookings);
        return doc;
    }
}
