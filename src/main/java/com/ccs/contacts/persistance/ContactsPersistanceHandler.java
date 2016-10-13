package com.ccs.contacts.persistance;

import com.ccs.contacts.service.model.Contact;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class ContactsPersistanceHandler {
    public List<Contact> getAll() {
        throw new NotImplementedException();

/*
        return Arrays.asList(
                new Contact("1", "a1", "a2", "a3"),
                new Contact("2", "b1", "b2", "b3"),
                new Contact("3", "c1", "c2", "c3"));
*/
    }

    public boolean contactExists(Contact contact) {
        throw new NotImplementedException();
    }

    public String generateUniqueContactId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

/*
    public class ContactsPersistanceHandler extends CouchDbRepositorySupport<Contact> {
    protected ContactsPersistanceHandler(CouchDbConnector db) {
        super(Contact.class, db);
    }
*/
}
