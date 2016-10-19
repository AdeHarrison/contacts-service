package com.ccs.contacts.api;

import com.ccs.ContactsServiceApplication;
import com.ccs.config.TestConfig;
import com.ccs.contacts.api.dto.AlreadyExistsException;
import com.ccs.contacts.service.ContactsService;
import com.ccs.contacts.service.model.Contact;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.ccs.contacts.util.ContactsTestDataUtil.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class, ContactsServiceApplication.class})
public class ContactsAPIControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ContactsService contactsService;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void handleAlreadyExistsExceptionWhenCreatingContactThatExists() throws Exception {

        when(contactsService.createContact(any(Contact.class))).thenThrow(new AlreadyExistsException("exception message"));

        mvc.perform(
                post("/contacts")
                        .content(getTestContactAsJson())
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.detail", is("exception message")));
    }

    @Test
    public void successfullyCreateNewContact() throws Exception {

        when(contactsService.createContact(any(Contact.class))).thenReturn(getTestContact());

        mvc.perform(post("/contacts")
                .content(getTestContactAsJson())
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.contactId", is(CONTACT_ID_1)))
                .andExpect(jsonPath("$.firstName", is(CONTACT_FIRSTNAME_1)))
                .andExpect(jsonPath("$.lastName", is(CONTACT_LASTNAME_1)));
    }

    @Test
    public void handleNotExistsExceptionWhenUpdatingContactThatDoesNotExist() throws Exception {
        assertFalse(true);
    }

    @Test
    public void successfullyUpdateContact() throws Exception {
        assertFalse(true);
    }

    @Test
    public void handleNotExistsExceptionWhenDeletingContactThatDoesNotExist() throws Exception {
        assertFalse(true);
    }

    @Test
    public void successfullyDeleteContact() throws Exception {
        assertFalse(true);
    }

    @Test
    public void getAllContacts() throws Exception {


        when(contactsService.getContacts()).thenReturn(getTestContacts());

        mvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contactId", is(CONTACT_ID_1)))
                .andExpect(jsonPath("$[0].firstName", is(CONTACT_FIRSTNAME_1)))
                .andExpect(jsonPath("$[0].lastName", is(CONTACT_LASTNAME_1)))
                .andExpect(jsonPath("$[1].contactId", is(CONTACT_ID_2)))
                .andExpect(jsonPath("$[1].firstName", is(CONTACT_FIRSTNAME_2)))
                .andExpect(jsonPath("$[1].lastName", is(CONTACT_LASTNAME_2)))
                .andExpect(jsonPath("$[2].contactId", is(CONTACT_ID_3)))
                .andExpect(jsonPath("$[2].firstName", is(CONTACT_FIRSTNAME_3)))
                .andExpect(jsonPath("$[2].lastName", is(CONTACT_LASTNAME_3)));

    }

    @Test
    public void getContact() throws Exception {
        Contact expected = getTestContact();

        when(contactsService.getContact(anyString())).thenReturn(expected);

        mvc.perform(get("/contacts/" + CONTACT_ID_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactId", is(CONTACT_ID_1)))
                .andExpect(jsonPath("$.firstName", is(CONTACT_FIRSTNAME_1)))
                .andExpect(jsonPath("$.lastName", is(CONTACT_LASTNAME_1)));
    }
}