package com.ccs.contacts.api;

import com.ccs.ContactsServiceApplication;
import com.ccs.contacts.api.dto.AlreadyExistsException;
import com.ccs.contacts.service.ContactsService;
import com.ccs.contacts.service.model.Contact;
import com.ccs.config.TestConfig;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.ccs.contacts.util.ContactsTestDataUtil.*;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class, ContactsServiceApplication.class})
public class ContactsAPIControllerTest {

    private static String CONTACT_ID1 = "99999";

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
                .andExpect(jsonPath("$.contactId", is("1")))
                .andExpect(jsonPath("$.firstName", is("a1")))
                .andExpect(jsonPath("$.lastName", is("a2")));
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
    public void getContact() throws Exception {
        Contact expected = getTestContact();

        when(contactsService.getContact(anyString())).thenReturn(expected);

        mvc.perform(get("/contacts/" + "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactId", is("1")))
                .andExpect(jsonPath("$.firstName", is("a1")))
                .andExpect(jsonPath("$.lastName", is("a2")));
    }

    @Test
    public void getAllContacts() throws Exception {

        when(contactsService.getContacts()).thenReturn(getTestContacts());

        mvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contactId", is("1")))
                .andExpect(jsonPath("$[0].firstName", is("a1")))
                .andExpect(jsonPath("$[0].lastName", is("a2")))
                .andExpect(jsonPath("$[1].contactId", is("2")))
                .andExpect(jsonPath("$[1].firstName", is("b1")))
                .andExpect(jsonPath("$[1].lastName", is("b2")))
                .andExpect(jsonPath("$[2].contactId", is("3")))
                .andExpect(jsonPath("$[2].firstName", is("c1")))
                .andExpect(jsonPath("$[2].lastName", is("c2")));

    }
}