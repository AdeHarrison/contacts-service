package com.ccs.contacts.persistance;

import com.ccs.contacts.api.dto.AlreadyExistsException;
import com.ccs.contacts.service.model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.ccs.contacts.util.ContactsTestDataUtil.getTestContact;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
        String id = contactsPersistanceHandler.generateUniqueContactId();

        assertThat(id.length(), is(32));
        assertThat(id.contains("-"), is(false));
    }
}