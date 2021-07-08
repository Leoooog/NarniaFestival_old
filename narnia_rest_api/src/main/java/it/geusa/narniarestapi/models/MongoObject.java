package it.geusa.narniarestapi.models;

import org.bson.Document;

public interface MongoObject {
    Document toDocument();
}
