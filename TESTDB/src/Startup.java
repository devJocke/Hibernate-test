import data.Unicorn;
import data.UnicornClass;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class Startup {

    public static void main(String[] args) {
        Session session;

        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();

            Unicorn unicorn = new Unicorn.UnicornBuilder("Stefan", "Stefansson", "Steffe").build();
            UnicornClass unicornClass = new UnicornClass("biologi", new Date('2'));
            unicornClass.getUnicorn().add(unicorn);
            session.save(unicornClass);

            session.getTransaction().commit();

            for (Unicorn unicorn1 : unicornClass.getUnicorn()) {
                System.out.println("unicornClass ID=" + unicorn1.getFirstName());
            }

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        session.close();
    }
}