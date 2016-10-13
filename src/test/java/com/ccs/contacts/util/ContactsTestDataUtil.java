package com.ccs.contacts.util;

import com.ccs.contacts.api.dto.ContactDTO;
import com.ccs.contacts.service.model.Contact;

import java.util.Arrays;
import java.util.List;

public class ContactsTestDataUtil {

    public static ContactDTO getTestContactDTO() {
        return new ContactDTO("1", "a1", "a2");
    }

    public static Contact getTestContact() {
        return new Contact("1", "a1", "a2");
    }

    public static List<Contact> getTestContacts() {
        return Arrays.asList(
                new Contact("1", "a1", "a2"),
                new Contact("2", "b1", "b2"),
                new Contact("3", "c1", "c2"));
    }

    public static String getTestContactAsJson() {
        return "{\"firstName\":\"a1\", \"lastName\": \"a3\"}";
    }

}
