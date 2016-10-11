package com.ccs.service;

import com.ccs.contacts.ContactsPersistanceHandler;
import com.ccs.contacts.service.ContactsService;
import com.ccs.contacts.service.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

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
    public void getContacts() throws Exception {

        Mockito.when(contactsPersistanceHandler.getAll()).thenReturn(getTestContacts());

        List<Contact> contacts = contactsService.getContacts();

        assertThat(contacts.size(), is(3));
    }

    private List<Contact> getTestContacts() {
        return Arrays.asList(
                new Contact("a1", "a2", "a3"),
                new Contact("b1", "b2", "b3"),
                new Contact("c1", "c2", "c3"));
    }
}