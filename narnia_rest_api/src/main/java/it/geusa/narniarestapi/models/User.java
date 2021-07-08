package it.geusa.narniarestapi.models;

import it.geusa.narniarestapi.models.enums.ProfileType;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class User implements MongoObject {
    private final ObjectId id;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final boolean emailConfirmed;
    private final boolean enabled;
    private final ProfileType profileType;
    private final List<FoodStamp> foodStamps;
    private final List<EventBooking> eventBookings;

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

    public User(ObjectId id, String username, String email, String firstName, String lastName, boolean emailConfirmed, boolean enabled, ProfileType profileType, List<FoodStamp> foodStamps, List<EventBooking> eventBookings) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailConfirmed = emailConfirmed;
        this.enabled = enabled;
        this.profileType = profileType;
        this.foodStamps = foodStamps;
        this.eventBookings = eventBookings;
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

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public List<FoodStamp> getFoodStamps() {
        return foodStamps;
    }

    public List<EventBooking> getEventBookings() {
        return eventBookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var user = (User) o;

        return id.equals(user.id);
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
