package com.ccs.contacts;

import com.ccs.contacts.service.model.Contact;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class ContactsPersistanceHandler extends CouchDbRepositorySupport<Contact> {
    protected ContactsPersistanceHandler(CouchDbConnector db) {
        super(Contact.class, db);
    }
}
