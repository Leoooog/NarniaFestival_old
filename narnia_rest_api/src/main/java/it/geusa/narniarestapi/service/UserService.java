package it.geusa.narniarestapi.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import it.geusa.narniarestapi.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class UserService {
    @Inject
    MongoClient mongoClient;

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (var cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                users.add(new User(d));
            }
        }
        return users;
    }

    public Optional<User> findUserById(String id) {
        return findUserById(new ObjectId(id));
    }
    public Optional<User> findUserById(ObjectId id) {
        Document d = getCollection().find(eq("_id", id)).first();
        return d == null ? Optional.empty() : Optional.of(new User(d));
    }

    public Optional<User> findUserByEmail(String email) {
        Document d = getCollection().find(eq("email", email)).first();
        return d == null ? Optional.empty() : Optional.of(new User(d));
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("narnia").getCollection("users");
    }
}
