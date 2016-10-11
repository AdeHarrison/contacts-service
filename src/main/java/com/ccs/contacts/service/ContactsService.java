package com.ccs.contacts.service;

import com.ccs.contacts.ContactsPersistanceHandler;
import com.ccs.contacts.service.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {

    @Autowired
    ContactsPersistanceHandler contactsPersistanceHandler;

    public List<Contact> getContacts() {
        return contactsPersistanceHandler.getAll();
    }
}
