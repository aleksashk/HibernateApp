package ru.philimonov;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.model.Person;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Person person1 = new Person("Test Person 1", 25);
            Person person2 = new Person("Test Person 2", 26);
            Person person3 = new Person("Test Person 3", 27);
            Person person4 = new Person("Test Person 4", 28);

            session.save(person1);
            session.save(person2);
            session.save(person3);
            session.save(person4);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            factory.close();
        }
    }
}
