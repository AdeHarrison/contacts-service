package com.ccs.contacts.api;

import com.ccs.contacts.api.dto.ContactDTO;
import com.ccs.contacts.service.ContactsService;
import com.ccs.contacts.service.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ContactsAPIController {

    @Autowired
    private ContactsService contactsService;

    @RequestMapping(path = "/contacts", method = GET)
    public ResponseEntity<List<ContactDTO>> getContacts() {

        List<Contact> contacts = contactsService.getContacts();
        List<ContactDTO> contactDTOs = convertContactsModelToDTOs(contacts);

        return ResponseEntity.ok(contactDTOs);
    }

    private List<ContactDTO> convertContactsModelToDTOs(List<Contact> contacts) {
        return contacts.stream().map(c -> new ContactDTO(c.getFirstName(), c.getMiddleName(), c.getLastName()))
                .collect(Collectors.toList());
    }
}
