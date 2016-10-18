package com.ccs.contacts.util;

import com.ccs.contacts.api.dto.ContactDTO;
import com.ccs.contacts.service.model.Contact;

import java.util.Arrays;
import java.util.List;

public class ContactsTestDataUtil {

    public static final int CONTACT_ID_1 = 1;
    public static final String CONTACT_FIRSTNAME_1 = "firstname1";
    public static final String CONTACT_LASTNAME_1 = "lastname1";
    public static final int CONTACT_ID_2 = 2;
    public static final String CONTACT_FIRSTNAME_2 = "firstname2";
    public static final String CONTACT_LASTNAME_2 = "lastname2";
    public static final int CONTACT_ID_3 = 3;
    public static final String CONTACT_FIRSTNAME_3 = "firstname3";
    public static final String CONTACT_LASTNAME_3 = "lastname3";

    public static ContactDTO getTestContactDTO() {
        return new ContactDTO(CONTACT_ID_1, CONTACT_FIRSTNAME_1, CONTACT_LASTNAME_1);
    }

    public static Contact getTestContact() {
        return new Contact(CONTACT_ID_1, CONTACT_FIRSTNAME_1, CONTACT_LASTNAME_1);
    }

    public static List<Contact> getTestContacts() {
        return Arrays.asList(
                new Contact(CONTACT_ID_1, CONTACT_FIRSTNAME_1, CONTACT_LASTNAME_1),
                new Contact(CONTACT_ID_2, CONTACT_FIRSTNAME_2, CONTACT_LASTNAME_2),
                new Contact(CONTACT_ID_3, CONTACT_FIRSTNAME_3, CONTACT_LASTNAME_3));
    }

    public static String getTestContactAsJson() {
        return "{\"firstName\":\"" + CONTACT_FIRSTNAME_1 + "\", \"lastName\": \"" + CONTACT_LASTNAME_1 + "\"}";
    }
}
