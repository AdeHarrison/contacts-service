package com.ccs.contacts.api;

import com.ccs.contacts.api.dto.AlreadyExistsException;
import com.ccs.contacts.api.dto.ContactDTO;
import com.ccs.contacts.api.dto.ErrorMessageDTO;
import com.ccs.contacts.service.ContactsService;
import com.ccs.contacts.service.model.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ccs.contacts.converter.ContactConverter.*;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Api(value = "Contacts API", description = "the contacts API")
public class ContactsAPIController {

    private static final Logger log = getLogger(ContactsAPIController.class);

    @Autowired
    private ContactsService contactsService;


    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ErrorMessageDTO handleContactAlreadyExists(AlreadyExistsException aee) {
        log.info(aee.getMessage());
        return new ErrorMessageDTO("Application already exists", aee.getMessage());
    }


    @ApiOperation(value = "Create a new contact", response = ContactDTO.class)
    @RequestMapping(path = "/contacts", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) throws AlreadyExistsException {

        Contact contact = contactsService.createContact(convertContactDTOToContact(contactDTO));
        ContactDTO contactResponse = convertContactToContactDTO(contact);

        return ResponseEntity.ok((contactResponse));
    }

    @ApiOperation(value = "Get a contact by id", notes = "Get a contact by id\n", response = Contact.class)
    @RequestMapping(path = "/contacts/{contactId}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDTO> getContact(@ApiParam(value = "the ID of the contact to retrieve", required = true)
                                                 @PathVariable("contactId") String contactId) {

        Contact contact = contactsService.getContact(contactId);
        ContactDTO contactDTO = convertContactModelToDTO(contact);

        return ResponseEntity.ok(contactDTO);
    }

    @ApiOperation(value = "Gets all contacts", notes = "Gets all contacts\n", response = Contact.class)
    @RequestMapping(path = "/contacts", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContactDTO>> getContacts() {

        List<Contact> contacts = contactsService.getContacts();
        List<ContactDTO> contactDTOs = convertContactsModelToDTOs(contacts);

        return ResponseEntity.ok(contactDTOs);
    }
}
