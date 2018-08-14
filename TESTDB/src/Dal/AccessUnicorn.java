package Dal;

import Dao.*;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Objects;

public class AccessUnicorn {

    private static Session session = SessionConfiguration.getSession();
    private static Unicorn unicorn;

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
            Objects.requireNonNull(unicorn.getCare()).loadAllNeeds();
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