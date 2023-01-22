package ru.philimonov;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.model.Item;
import ru.philimonov.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Person person = new Person("Jack", 25);
            Item item1 = new Item("Test item1");
            Item item2 = new Item("Test item2");
            Item item3 = new Item("Test item3");
            person.addItem(item1);
            person.addItem(item2);
            person.addItem(item3);

            session.save(person);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            factory.close();
        }
    }
}
