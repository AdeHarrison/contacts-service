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

        if (contactsPersistanceHandler.contactExists(contact)) {
            throw new AlreadyExistsException(String.format("Contact '%s, %s' already exists", contact.getFirstName(), contact.getLastName()));
        }

        contact.setContactId(contactsPersistanceHandler.generateUniqueContactId());

        return contact;
    }

    public List<Contact> getContacts() {
        return contactsPersistanceHandler.getAll();
    }

    public Contact getContact(String contactId) {
        throw new NotImplementedException();
    }
}
