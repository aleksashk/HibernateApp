package ru.philimonov;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.model.Item;
import ru.philimonov.model.Person;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("update Person set name='Test' where age < 30").executeUpdate();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            factory.close();
        }
    }
}
