package com.ccs.contacts.service;

import com.ccs.contacts.api.dto.AlreadyExistsException;
import com.ccs.contacts.api.dto.ContactDTO;
import com.ccs.contacts.persistance.ContactsPersistanceHandler;
import com.ccs.contacts.service.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.ccs.contacts.util.ContactsTestDataUtil.getTestContact;
import static com.ccs.contacts.util.ContactsTestDataUtil.getTestContactDTO;
import static com.ccs.contacts.util.ContactsTestDataUtil.getTestContacts;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ContactsServiceTest {

    @InjectMocks
    ContactsService contactsService;

    @Mock
    ContactsPersistanceHandler contactsPersistanceHandler;

    @Test
    public void addContacts() throws AlreadyExistsException {
        ContactDTO testContactDTO = getTestContactDTO();
        Contact testContact = getTestContact();

        Mockito.when(contactsService.createContact(testContact)).thenReturn(getTestContact());

        contactsService.createContact(testContact);

//        assertThat(contacts.size(), is(3));
    }

    @Test
    public void getContacts() {

        Mockito.when(contactsPersistanceHandler.getAll()).thenReturn(getTestContacts());

        List<Contact> contacts = contactsService.getContacts();

        assertThat(contacts.size(), is(3));
    }

}