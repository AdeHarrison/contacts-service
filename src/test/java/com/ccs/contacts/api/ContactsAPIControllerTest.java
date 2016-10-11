package com.ccs.contacts.api;

import com.ccs.ContactsServiceApplication;
import com.ccs.contacts.service.ContactsService;
import com.ccs.contacts.service.model.Contact;
import com.ccs.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.ccs.contacts.util.ContactsTestDataUtil.getTestContacts;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void getContacts() throws Exception {

        Mockito.when(contactsService.getContacts()).thenReturn(getTestContacts());

        mvc.perform(get("/contacts")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("a1")))
                .andExpect(jsonPath("$[0].middleName", is("a2")))
                .andExpect(jsonPath("$[0].lastName", is("a3")))
                .andExpect(jsonPath("$[1].firstName", is("b1")))
                .andExpect(jsonPath("$[1].middleName", is("b2")))
                .andExpect(jsonPath("$[1].lastName", is("b3")))
                .andExpect(jsonPath("$[2].firstName", is("c1")))
                .andExpect(jsonPath("$[2].middleName", is("c2")))
                .andExpect(jsonPath("$[2].lastName", is("c3")));

    }
}