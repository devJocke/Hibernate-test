import data.*;
import data.Unicorn;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.util.*;

public class Startup {

    public static void main(String[] args) {


        /**
         * 1. First time start, create new unicorn
         * ask for Unicorn properties fname(not null),lname,tname
         * Tell user that she needs to attend the unicorn and do the following
         * Play, Discipline, Flush
         *
         * -- New unicorn -> Care -> disc, play, flush
         * -- unicorn.getcare() -> Fetch all and check which values that are 0
         *
         * 2. Second start, unicorn is already created
         * Do a getCheck to see if any values are 0
         * --> Update attended property and somehow set a timer that will change the value to 0 <--
         *
         * 3. Nothing yet
         */

        Session session;

        try {
            session = new Configuration().configure()
                    .addAnnotatedClass(UnicornClass.class)
                    .addAnnotatedClass(Care.class)
                    .addAnnotatedClass(Unicorn.class)
                    .addAnnotatedClass(Flush.class)
                    .addAnnotatedClass(Discipline.class)
                    .addAnnotatedClass(Play.class)
                    .buildSessionFactory().openSession();

            session.beginTransaction();
            Care care = new Care(0, 0, 0);
            Unicorn unicorn = createNewUnicornFromUserInput();
            care.setUnicorn(unicorn);
            unicorn.setCare(care);
            session.save(unicorn);
            Query query = session.createNativeQuery("select * from Care inner join Unicorn U on Care.unicornId = U.id", Care.class);

            System.out.print(((Care) query.getSingleResult()).getDisciplineId());
            session.getTransaction().commit();
//
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        session.close();
    }

    private static Unicorn createNewUnicornFromUserInput() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter unicorn firstname: ");
        String firstName = userInput.next();

        System.out.print("Enter unicorn lastname: ");
        String lastName = userInput.next();

        System.out.print("Enter unicorn thirdname: ");
        String thirdName = userInput.next();

        userInput.close();
        System.out.println(" A new Unicorn has been created, take good care of him!");
        System.out.println(" Firstname : " + firstName);
        System.out.println(" Lastname : " + lastName);
        System.out.println(" Thirdname : " + thirdName);

        return new Unicorn.UnicornBuilder(firstName, lastName, thirdName).build();
    }

}