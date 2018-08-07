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
            session.getTransaction().commit();
            return unicorn;
        } catch (NoResultException e) {
        }
        session.getTransaction().commit();
        return new Unicorn();
    }

    public static Unicorn getUnicorn(int unicornId) {

        session.beginTransaction();
        TypedQuery<Unicorn> unicornTypedQuery = session.createNativeQuery("select * from Unicorn where id = (?1)", Unicorn.class);
        unicornTypedQuery.setParameter(1, unicornId);
        unicorn = unicornTypedQuery.getSingleResult();
        session.getTransaction().commit();
        return unicorn;
    }

    public static void updateUnicornNeeds(Unicorn unicorn) {
        session.beginTransaction();
        session.saveOrUpdate(unicorn);
        session.getTransaction().commit();
    }

    public static void checkForUpdatedData(Unicorn unicorn) {

        session.beginTransaction();
        session.refresh(unicorn);
        session.getTransaction().commit();
        unicorn.getCare().loadAllNeeds();
    }
}