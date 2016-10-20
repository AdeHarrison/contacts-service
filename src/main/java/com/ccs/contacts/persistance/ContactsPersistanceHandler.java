package com.ccs.contacts.persistance;

import com.ccs.contacts.service.model.Contact;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class ContactsPersistanceHandler {

    SessionFactory factory;

    public List<Contact> getAll() {

        factory = new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Contact");
        List<Contact> contacts = query.list();
        contacts.forEach(System.out::println);
        tx.commit();
        session.close();

        return contacts;
    }

    public boolean contactExists(Contact contact) {
        throw new NotImplementedException();
    }

    public int generateUniqueContactId() {
        return 1;
//        return UUID.randomUUID().toString().replaceAll("-", "");
    }

/*
    public class ContactsPersistanceHandler extends CouchDbRepositorySupport<Contact> {
    protected ContactsPersistanceHandler(CouchDbConnector db) {
        super(Contact.class, db);
    }
*/
}
