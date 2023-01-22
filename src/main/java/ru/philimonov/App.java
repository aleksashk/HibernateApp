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
            Person person = session.get(Person.class, 10);
            Person person1 = new Person("Some name for Person", 56);
            person.setName("New test name");
            session.save(person1);
            session.delete(person);
            session.getTransaction().commit();
            System.out.println(person1.getId());
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            factory.close();
        }
    }
}
