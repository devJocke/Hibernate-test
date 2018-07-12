import data.Unicorn;
import data.UnicornClass;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Startup {

    public static void main(String[] args) {
        Session session;

        try {
            session = new Configuration().configure().addAnnotatedClass(Unicorn.class).addAnnotatedClass(UnicornClass.class).buildSessionFactory().openSession();
            session.beginTransaction();

            Unicorn unicorn = new Unicorn.UnicornBuilder("Stefan", "Stefansson", "Steffe").build();
            Unicorn unicorn2 = new Unicorn.UnicornBuilder("börje", "börjesson", "börje").build();
            UnicornClass unicornClass = new UnicornClass.UnicornClassBuilder("biologi", new Date('2')).build();

            unicornClass.setUnicorn(unicorn);
            session.save(unicorn);
            session.save(unicornClass);

            session.getTransaction().commit();

            session.beginTransaction();
            UnicornClass unicornClass1 = session.load(UnicornClass.class, 0);
            System.out.println("Unicorn named " + unicornClass1.getUnicorn().getFirstName());
            session.getTransaction().commit();

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        System.out.println("Done");
        session.close();
    }
}