package com.ccs.contacts;

import com.ccs.contacts.service.model.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by ade on 18/10/16.
 */
public class Test {
    private static SessionFactory factory;

    public static void main(String[] args) {
        factory = new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = null;

        tx = session.beginTransaction();
        List<Contact> contacts = session.createQuery("FROM Contact").list();
        tx.commit();
    }
}
