package it.geusa.narniarestapi.service;

import com.mongodb.client.MongoClient;
import it.geusa.narniarestapi.model.EventBooking;
import it.geusa.narniarestapi.model.User;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class EventBookingService {

    @Inject
    UserService userService;

    public List<EventBooking> findAllEventBookings() {
        List<User> users = userService.findAllUsers();
        return users.stream().map(User::getEventBookings).filter(eventBookings ->!eventBookings.isEmpty()).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<EventBooking> findEventBookingsByEventId(ObjectId eventId) {
        return findAllEventBookings().stream().filter(e->e.getEventId().equals(eventId)).collect(Collectors.toList());
    }

    public List<EventBooking> findUserEventBookings(ObjectId userId) {
        Optional<User> userOptional = userService.findUserById(userId);
        if(userOptional.isEmpty()) return Collections.emptyList();
        return userOptional.get().getEventBookings();
    }

    public Optional<EventBooking> findEventBookingById(ObjectId eventBookingId) {
        return findAllEventBookings().stream().filter(e->e.getId().equals(eventBookingId)).findFirst();
    }
}
