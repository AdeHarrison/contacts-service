package com.ccs.contacts.util;

import com.ccs.contacts.service.model.Contact;

import java.util.Arrays;
import java.util.List;

public class ContactsTestDataUtil {

    public static List<Contact> getTestContacts() {
        return Arrays.asList(
                new Contact(1, "a1", "a2", "a3"),
                new Contact(2, "b1", "b2", "b3"),
                new Contact(3, "c1", "c2", "c3"));
    }
}