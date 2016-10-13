package com.ccs.contacts.converter;

import com.ccs.contacts.api.dto.ContactDTO;
import com.ccs.contacts.service.model.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class ContactConverter {

    public static Contact convertContactDTOToContact(ContactDTO contactDTO) {
        return new Contact(contactDTO.getContactId(), contactDTO.getFirstName(), contactDTO.getLastName());
    }

    public static ContactDTO convertContactToContactDTO(Contact contact) {
        return new ContactDTO(contact.getContactId(), contact.getFirstName(), contact.getLastName());
    }

    public static List<ContactDTO> convertContactsModelToDTOs(List<Contact> contacts) {
        return contacts.stream()
                .map(c -> convertContactModelToDTO(c))
                .collect(Collectors.toList());
/*
        return contacts.stream().map(c -> new ContactDTO(c.getContactId(), c.getFirstName(), c.getMiddleName(), c.getLastName()))
                .collect(Collectors.toList());
*/
    }

    public static ContactDTO convertContactModelToDTO(Contact contact) {
        return new ContactDTO(contact.getContactId(), contact.getFirstName(), contact.getLastName());
    }
}
