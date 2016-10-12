package com.ccs.contacts.api;

import com.ccs.contacts.api.dto.ContactDTO;
import com.ccs.contacts.service.ContactsService;
import com.ccs.contacts.service.model.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.ccs.contacts.converter.ContactConverter.convertContactDTOToContact;
import static com.ccs.contacts.converter.ContactConverter.convertContactToContactDTO;
import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Api(value = "Contacts API", description = "the contacts API")
public class ContactsAPIController {

    @Autowired
    private ContactsService contactsService;

    @ApiOperation(value = "Create a new contact", response = ContactDTO.class)
    @RequestMapping(path = "/contacts", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDTO> getContacts(@RequestBody ContactDTO contactDTO) {

        Contact contact = contactsService.addContact(convertContactDTOToContact(contactDTO));
        ContactDTO contactResponse = convertContactToContactDTO(contact);

        return ResponseEntity.ok((contactResponse));
    }

    @ApiOperation(value = "Gets all contacts", notes = "Gets all contacts\n", response = Contact.class)
    @RequestMapping(path = "/contacts", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContactDTO>> getContacts() {

        List<Contact> contacts = contactsService.getContacts();
        List<ContactDTO> contactDTOs = convertContactsModelToDTOs(contacts);

        return ResponseEntity.ok(contactDTOs);
    }

    private List<ContactDTO> convertContactsModelToDTOs(List<Contact> contacts) {
        return contacts.stream().map(c -> new ContactDTO(c.getContactId(), c.getFirstName(), c.getMiddleName(), c.getLastName()))
                .collect(Collectors.toList());
    }
}
