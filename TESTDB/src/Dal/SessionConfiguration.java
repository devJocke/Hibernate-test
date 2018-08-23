package Dal;

import Dao.*;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

public class SessionConfiguration {

    private static Session session = setupSessionConfiguration();

    private static Session setupSessionConfiguration() {
        return new Configuration().configure()
                .addAnnotatedClass(Care.class)
                .addAnnotatedClass(Unicorn.class)
                .addAnnotatedClass(Toilet.class)
                .addAnnotatedClass(Discipline.class)
                .addAnnotatedClass(Play.class)
                .addAnnotatedClass(Farm.class)
//                .addAnnotatedClass(Blueprints.class)
                .buildSessionFactory().openSession();
    }

    public static Session getSession() {
        return session;
    }
}
