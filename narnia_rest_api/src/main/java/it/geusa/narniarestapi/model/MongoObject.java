package it.geusa.narniarestapi.model;

import org.bson.Document;

public interface MongoObject {
    Document toDocument();
}
