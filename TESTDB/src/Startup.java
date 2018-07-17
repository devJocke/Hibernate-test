import data.Unicorn;
import data.UnicornClass;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Startup {

    public static void main(String[] args) {
        Session session;

        try {
            session = new Configuration().configure().addAnnotatedClass(Unicorn.class).addAnnotatedClass(UnicornClass.class).buildSessionFactory().openSession();

            List<UnicornClass> unicornClasses = new ArrayList<>();
            Query query = session.createQuery("select distinct title from UnicornClass");

            if (query.list().isEmpty()) {
                unicornClasses = setupClasses();
            }

            List<Unicorn> unicornss = setupUnicorns();

            session.beginTransaction();

            for (UnicornClass unicornClass : unicornClasses) {
                for (Unicorn unicorn : unicornss) {
                    session.save(setClassWithUnicorn(unicornClass, unicorn));
                }
            }

            for (UnicornClass unicornClass : unicornClasses) {
                for (Unicorn unicorn : unicornss) {
                    session.save(setUnicornInClass(unicorn, unicornClass));
                }
            }
            session.getTransaction().commit();
             
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        System.out.println("Done");
        session.close();
    }

    private static List<UnicornClass> setupClasses() {
        List<UnicornClass> unicornClasses = new ArrayList<>();
        unicornClasses.add(new UnicornClass.UnicornClassBuilder("biologi", new Date('2')).build());
        unicornClasses.add(new UnicornClass.UnicornClassBuilder("it", new Date('3')).build());
        return unicornClasses;
    }

    private static List<Unicorn> setupUnicorns() {
        List<Unicorn> unicornClasses = new ArrayList<>();
        unicornClasses.add(new Unicorn.UnicornBuilder("Stefan", "Stefansson", "Steffe").build());
        unicornClasses.add(new Unicorn.UnicornBuilder("börje", "börjesson", "börje").build());
        return unicornClasses;
    }

    private static Unicorn setUnicornInClass(Unicorn unicorn, UnicornClass unicornClass) {
        unicorn.getUnicornClass().add(unicornClass);
        return unicorn;
    }

    private static UnicornClass setClassWithUnicorn(UnicornClass unicornClass, Unicorn unicorn) {
        unicornClass.getUnicorns().add(unicorn);
        return unicornClass;
    }
}