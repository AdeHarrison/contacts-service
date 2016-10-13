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
import static java.util.Collections.emptyList;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ContactsServiceTest {

    private static String DEFAULT_CONTACT_ID1 = "99999";

    @InjectMocks
    ContactsService contactsService;

    @Mock
    ContactsPersistanceHandler contactsPersistanceHandler;

    @Test(expected = AlreadyExistsException.class)
    public void shouldThrowAlreadyExistsExceptionWhenContactAlreadyExists() throws AlreadyExistsException {
        when(contactsPersistanceHandler.contactExists(any(Contact.class))).thenReturn(true);
        contactsService.createContact(getTestContact());
   }

    @Test
    public void successfullyCreateNewContact() throws AlreadyExistsException {
        Contact testContact = getTestContact();

        when(contactsPersistanceHandler.generateUniqueContactId()).thenReturn(DEFAULT_CONTACT_ID1);

        Contact actual = contactsService.createContact(testContact);

        assertThat(actual.getContactId(), is(DEFAULT_CONTACT_ID1));

//        assertThat(contacts.size(), is(3));
    }

    @Test
    public void getContacts() {

        Mockito.when(contactsPersistanceHandler.getAll()).thenReturn(getTestContacts());

        List<Contact> contacts = contactsService.getContacts();

        assertThat(contacts.size(), is(3));
    }

}