package com.ccs.contacts.service;

import com.ccs.contacts.api.dto.AlreadyExistsException;
import com.ccs.contacts.persistance.ContactsPersistanceHandler;
import com.ccs.contacts.service.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.ccs.contacts.util.ContactsTestDataUtil.CONTACT_ID_1;
import static com.ccs.contacts.util.ContactsTestDataUtil.getTestContact;
import static com.ccs.contacts.util.ContactsTestDataUtil.getTestContacts;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ContactsServiceTest {

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
        Contact expected = getTestContact();

        when(contactsPersistanceHandler.generateUniqueContactId()).thenReturn(CONTACT_ID_1);

        Contact actual = contactsService.createContact(expected);

        assertThat(actual.getContactId(), is(expected.getContactId()));
        assertThat(actual.getFirstName(), is(expected.getFirstName()));
        assertThat(actual.getLastName(), is(expected.getLastName()));
    }

    @Test
    public void getContacts() {
        Mockito.when(contactsPersistanceHandler.getAll()).thenReturn(getTestContacts());

        List<Contact> contacts = contactsService.getContacts();

        assertThat(contacts.size(), is(3));
    }
}