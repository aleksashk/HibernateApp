package ru.philimonov;

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

            Item item = session.get(Item.class, 1);
            System.out.println("We got an item!!!");
            System.out.println(item.getOwner());
            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
