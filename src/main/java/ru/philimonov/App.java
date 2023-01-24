package ru.philimonov;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.model.Item;
import ru.philimonov.model.Person;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory factory = configuration.buildSessionFactory();
        try (factory) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 8);
            System.out.println("We have got a person!");
            Hibernate.initialize(person.getItemList());

            session.getTransaction().commit();
            System.out.println(person.getItemList());

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
