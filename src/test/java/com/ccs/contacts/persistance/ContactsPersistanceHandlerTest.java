package com.ccs.contacts.persistance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
public class ContactsPersistanceHandlerTest {

    ContactsPersistanceHandler contactsPersistanceHandler;

    @Before
    public void setup() {
        contactsPersistanceHandler = new ContactsPersistanceHandler();
    }

    @Test
    public void getAll() throws Exception {
        assertFalse(true);
    }

    @Test
    public void contactExists() throws Exception {
        assertFalse(true);
    }

    @Test
    public void successfullyGenerateNewContactId() {
        int id = contactsPersistanceHandler.generateUniqueContactId();

/*
        assertThat(id.length(), is(32));
        assertThat(id.contains("-"), is(false));
*/
    }
}