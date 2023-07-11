package org.paul;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Laptop.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

       /* Random random = new Random();
        for (int i = 0; i <= 50; i++) {
            Laptop laptop = new Laptop();
            laptop.setLaptopId(i);
            laptop.setLaptopName("Laptop Brand: " + i);
            laptop.setPrice(random.nextInt(20000, 80000));
            session.save(laptop);
        }*/

       /* Laptop laptop = new Laptop();   // Its now in transient state
        laptop.setLaptopId(52);
        laptop.setLaptopName("Macbook Air");
        laptop.setPrice(85000);

        session.save(laptop); // Now its in persistent state
        session.remove(laptop); // Now it will remove data*/

        Laptop laptop = session.get(Laptop.class, 51); // Everytime we use get it will hit dB
        // Laptop laptop = session.load(Laptop.class, 51); // Load will give us the proxy object
        System.out.println(laptop);


        session.getTransaction().commit();

        /*session.detach(laptop); // Now it will detach from dB
        laptop.setPrice(80000);*/

    }
}
