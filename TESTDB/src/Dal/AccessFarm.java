package Dal;

import Dao.*;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccessFarm {

    private static Session session = SessionConfiguration.getSession();
    private static Farm farm;

    public static Farm preLoadFarm() {

        try {
            session.beginTransaction();
            TypedQuery<Farm> unicornTypedQuery = session.createNativeQuery("select * from Farm", Farm.class);
            farm = unicornTypedQuery.getSingleResult();
            //Objects.requireNonNull(farm.getCare()).loadAllNeeds();
            session.getTransaction().commit();
            return farm;
        } catch (NoResultException ex) {
            System.err.println("No result retrieved when trying to load Farm, creating backup instead" + ex);
            //TODO HANDLE BACKUP
            return new Farm();
        }
    }

//    public static void createUnicorn(Unicorn setNewUnicorn) {
//        session.beginTransaction();
//        session.save(setNewUnicorn);
//        session.getTransaction().commit();
//    }
//
//    public static Unicorn getUnicorn(int unicornId) {
//
//        session.beginTransaction();
//        TypedQuery<Unicorn> unicornTypedQuery = session.createNativeQuery("select * from Unicorn where id = (?1)", Unicorn.class);
//        unicornTypedQuery.setParameter(1, unicornId);
//        farm = unicornTypedQuery.getSingleResult();
//        session.getTransaction().commit();
//        return farm;
//    }
//
//    public static void updateUnicornNeeds(Unicorn unicorn) {
//        session.beginTransaction();
//        session.saveOrUpdate(unicorn);
//        session.getTransaction().commit();
//    }
//
//    public static void checkForUpdatedData(Unicorn unicorn) {
//
//        session.beginTransaction();
//        session.refresh(unicorn);
//        session.getTransaction().commit();
//        unicorn.getCare().loadAllNeeds();
//    }
}