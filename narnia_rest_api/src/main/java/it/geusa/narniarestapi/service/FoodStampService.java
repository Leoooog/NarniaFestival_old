package it.geusa.narniarestapi.service;

import it.geusa.narniarestapi.model.FoodStamp;
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
public class FoodStampService {
    @Inject
    UserService userService;

    public List<FoodStamp> findAllFoodStamps() {
        List<User> users = userService.findAllUsers();
        return users.stream().map(User::getFoodStamps).filter(foodStamps ->!foodStamps.isEmpty()).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<FoodStamp> findUserFoodStamps(ObjectId userId) {
        Optional<User> userOptional = userService.findUserById(userId);
        if(userOptional.isEmpty()) return Collections.emptyList();
        return userOptional.get().getFoodStamps();
    }

    public Optional<FoodStamp> findFoodStampById(ObjectId foodStampId) {
        return findAllFoodStamps().stream().filter(e->e.getId().equals(foodStampId)).findFirst();
    }
}
