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


        Contact c1 = new Contact(4, "4", "44");
tx = session.beginTransaction();
        session.save(c1);
        tx.commit();


        tx = session.beginTransaction();
        List<Contact> contacts = session.createQuery("FROM Contact").list();
        contacts.forEach(System.out::println);
        tx.commit();
    }
}
