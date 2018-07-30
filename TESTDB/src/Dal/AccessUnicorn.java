package Dal;

import Data.*;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;

public class AccessUnicorn {

    static Session session = setupSessionConfiguration();

    private static Session setupSessionConfiguration() {
        return new Configuration().configure()
                .addAnnotatedClass(UnicornClass.class)
                .addAnnotatedClass(Care.class)
                .addAnnotatedClass(Unicorn.class)
                .addAnnotatedClass(Flush.class)
                .addAnnotatedClass(Discipline.class)
                .addAnnotatedClass(Play.class)
                .buildSessionFactory().openSession();
    }


    public static void createUnicorn(Unicorn newUnicorn) {
        session.beginTransaction();
        session.save(newUnicorn);
        session.getTransaction().commit();
    }

    public static Unicorn getAllUnicorns() {

        session.beginTransaction();
        TypedQuery<Unicorn> unicornTypedQuery = session.createNativeQuery("select * from Unicorn", Unicorn.class);
        Unicorn unicorn = unicornTypedQuery.getSingleResult();
        session.getTransaction().commit();

        return unicorn;
    }

    public static void updateNeeds(Unicorn unicorn) {
        session.beginTransaction();
        session.saveOrUpdate(unicorn);
        session.getTransaction().commit();
    }
}
