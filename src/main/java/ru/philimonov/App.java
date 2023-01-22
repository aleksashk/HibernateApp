package ru.philimonov;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.model.Item;
import ru.philimonov.model.Person;

import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Person person = new Person("new Person", 24);
            Item newItem = new Item("Item from Hibernate", person);
            person.setItemList(new ArrayList<>(Collections.singletonList(newItem)));
            session.save(person);
            session.save(newItem);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            factory.close();
        }
    }
}
