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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class, ContactsServiceApplication.class})
@WebAppConfiguration
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
                .andExpect(jsonPath("$.contactId", is("1")))
                .andExpect(jsonPath("$.firstName", is("a1")))
                .andExpect(jsonPath("$.middleName", is("a2")))
                .andExpect(jsonPath("$.lastName", is("a3")));
    }


    @Test
    public void updateContact() throws Exception {

    }

/*    @Test
    public void deleteContact() throws Exception {
    }

    @Test
    public void getContact() throws Exception {
    }
*/

    @Test
    public void getAllContacts() throws Exception {

        when(contactsService.getContacts()).thenReturn(getTestContacts());

        mvc.perform(get("/contacts")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contactId", is("1")))
                .andExpect(jsonPath("$[0].firstName", is("a1")))
                .andExpect(jsonPath("$[0].middleName", is("a2")))
                .andExpect(jsonPath("$[0].lastName", is("a3")))
                .andExpect(jsonPath("$[1].contactId", is("2")))
                .andExpect(jsonPath("$[1].firstName", is("b1")))
                .andExpect(jsonPath("$[1].middleName", is("b2")))
                .andExpect(jsonPath("$[1].lastName", is("b3")))
                .andExpect(jsonPath("$[2].contactId", is("3")))
                .andExpect(jsonPath("$[2].firstName", is("c1")))
                .andExpect(jsonPath("$[2].middleName", is("c2")))
                .andExpect(jsonPath("$[2].lastName", is("c3")));

    }
}