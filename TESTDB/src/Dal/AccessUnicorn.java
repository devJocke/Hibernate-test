package Dal;

import Data.*;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccessUnicorn {

    private static Session session = setupSessionConfiguration();
    private static Unicorn unicorn;

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

    public static Unicorn preLoadUnicorns() {

        try {
            session.beginTransaction();
            TypedQuery<Unicorn> unicornTypedQuery = session.createNativeQuery("select * from Unicorn", Unicorn.class);
            unicorn = unicornTypedQuery.getSingleResult();
            unicorn.getCare().loadAllNeeds();

            return unicorn;
        } catch (NoResultException e) {
        }
        session.getTransaction().commit();
        return new Unicorn();
    }

    public static Unicorn getUnicorn(int unicornId) {

        session.beginTransaction();
        session.getTransaction().commit();
        return unicorn;
    }

    public static void updateUnicornNeeds(Unicorn unicorn) {
        session.beginTransaction();
        session.saveOrUpdate(unicorn);
        session.getTransaction().commit();
    }
}
