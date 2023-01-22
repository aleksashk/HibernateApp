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

            Person person = session.get(Person.class, 3);
            List<Item> itemList = person.getItemList();
            System.out.println(itemList);

            Item item = session.get(Item.class, 5);
            Person owner = item.getOwner();
            System.out.println("owner is: " + owner);

            Person person1 = session.get(Person.class, 2);
            List<Item> itemList1 = person1.getItemList();
            Item item1 = new Item("Chicken");
            itemList1.add(item1);
            System.out.println(itemList1 + " owner is " + person1);

            Item item2 = session.get(Item.class, 7);
            System.out.println(item2.getOwner());
            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            factory.close();
        }
    }
}
