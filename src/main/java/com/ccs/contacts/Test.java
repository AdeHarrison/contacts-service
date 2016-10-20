package com.ccs.contacts;

import com.ccs.contacts.service.model.Address;
import com.ccs.contacts.service.model.AddressType;
import com.ccs.contacts.service.model.Contact;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by ade on 18/10/16.
 */
public class Test {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        Test test = new Test();
//        test.testContact();
        test.testAddress();
//        test.testAddressType();

    }

    private void testContact() {
        Session session = null;
        Transaction tx = null;

        session = factory.openSession();
        tx = session.beginTransaction();
        Contact c1 = new Contact(4, "4", "44");
        session.save(c1);
        tx.commit();
        session.close();

        session = factory.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("FROM Contact where contactId=4");
        List<Contact> contacts = query.list();
        contacts.forEach(System.out::println);
        tx.commit();
        session.close();

        session = factory.openSession();
        tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Contact.class);
        criteria.setMaxResults(5);
        tx.commit();
        session.close();

        session = factory.openSession();
        tx = session.beginTransaction();
        Criteria criteria1 = session.createCriteria(Contact.class);
        criteria1.add(Restrictions.like("firstName", "4"));
        List<Contact> contacts1 = criteria1.list();
        contacts1.forEach(System.out::println);
        tx.commit();
        session.close();

        session = factory.openSession();
        tx = session.beginTransaction();
        Criteria criteria2 = session.createCriteria(Contact.class);
        criteria2.add(Restrictions.ne("lastName", "44"));
        criteria2.setMaxResults(2);
        List<Contact> contacts2 = criteria2.list();
        contacts2.forEach(System.out::println);
        tx.commit();
        session.close();
    }

    private void testAddress() {
        Session session = null;
        Transaction tx = null;

        session = factory.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("From Address");
        List<Address> list = query.list();
        System.out.println(list.get(0).getAddressType().getDescription());
        list.forEach(System.out::println);
        tx.commit();
        session.close();

        session = factory.openSession();
        tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Address.class);
        criteria.setMaxResults(3);
        criteria.add(Restrictions.between("addressId", 2, 3));
        List<Address> list2 = criteria.list();
        list2.forEach(System.out::println);
        tx.commit();
        session.close();
    }

    private void testAddressType() {
        Session session = null;
        Transaction tx = null;

        session = factory.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("FROM AddressType");
        List<AddressType> list = query.list();
        list.forEach(System.out::println);
        tx.commit();
        session.close();

        session = factory.openSession();
        tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(AddressType.class);
        criteria.add(Restrictions.between("addressTypeId", 2, 3));
        List<AddressType> list2 = criteria.list();
        list2.forEach(System.out::println);
        tx.commit();
        session.close();
    }
}
