package com.ccs.contacts.converter;

import com.ccs.contacts.api.dto.ContactDTO;
import com.ccs.contacts.service.model.Contact;

public class ContactConverter {

    public static Contact convertContactDTOToContact(ContactDTO contactDTO) {
        return new Contact(contactDTO.getContactId(), contactDTO.getFirstName(), contactDTO.getMiddleName(), contactDTO.getLastName());
    }

    public static ContactDTO convertContactToContactDTO(Contact contact) {
        return new ContactDTO(contact.getContactId(), contact.getFirstName(), contact.getMiddleName(), contact.getLastName());
    }
}
