package com.ccs.contacts.service;

import com.ccs.contacts.api.dto.AlreadyExistsException;
import com.ccs.contacts.persistance.ContactsPersistanceHandler;
import com.ccs.contacts.service.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;

@Service
public class ContactsService {

    @Autowired
    ContactsPersistanceHandler contactsPersistanceHandler;

    public Contact createContact(Contact contact) throws AlreadyExistsException {

        if(contactsPersistanceHandler.contactExists(contact)) {
            throw new AlreadyExistsException(String.format("Contact '%s, %s, %s' already exists", contact.getFirstName(), contact.getMiddleName(), contact.getLastName()));
        }

        contact.setContactId(contactsPersistanceHandler.generateUniqueContactId());

        return contact;
    }

    public List<Contact> getContacts() {
        return Arrays.asList(
                new Contact("1", "a1", "a2", "a3"),
                new Contact("2", "b1", "b2", "b3"),
                new Contact("3", "c1", "c2", "c3"));
    }

    public Contact getContact(String contactId) {
        throw new NotImplementedException();
        //return null;
    }
}
