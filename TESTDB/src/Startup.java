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
            unicorn.setCare(care);
            session.save(unicorn);
            Query query = session.createNativeQuery("select * from Care inner join Unicorn U on Care.id = U.unicornCareId", Care.class);

           System.out.print(((Care) query.getSingleResult()).getDisciplineId());
//            List<UnicornClass> unicornClasses;
//            TypedQuery<UnicornClass> query = session.createNativeQuery("select * from UnicornClass  where UnicornClass.subject = 'biologi'", UnicornClass.class);
//
//            session.beginTransaction();
//
//            List<Unicorn> unicorns = setupUnicorns();
//
//
//            if (query.getResultList().isEmpty()) {
//                unicornClasses = setupClasses();
//                for (UnicornClass unicornClass1 : unicornClasses) {
//                    for (Unicorn unicorn : unicorns) {
//                        session.save(setClassWithUnicorn(unicornClass1, unicorn));
//                        session.save(setUnicornInClass(unicorn, unicornClass1));
//                    }
//                }
//            } else {
//                unicornClasses = query.getResultList();
//            }
//
//            Care care = new Care(1, 1, 1, 1, unicorns.get(0));
//            session.save(care);
//            System.out.println(unicorns.get(0).getCare());
//
//            System.out.println(unicornClasses.get(0).getSubject());
             session.getTransaction().commit();
//
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }


//        requestUnicornDiscipline();

//        updateDisciplineStatus(session);
//        System.out.println("Done");
        session.close();
    }

    private static void requestUnicornDiscipline() {

    }

    private static Unicorn createNewUnicornFromUserInput() {
        Scanner userInput = new Scanner(System.in);

//        System.out.print("Enter unicorn firstname: ");
//        String firstName = userInput.next();
//
//        System.out.print("Enter unicorn lastname: ");
//        String lastName = userInput.next();
//
//        System.out.print("Enter unicorn thirdname: ");
//        String thirdName = userInput.next();
//
//        userInput.close();
//        System.out.println(" A new Unicorn has been created, take good care of him!");
//        System.out.println(" Firstname : " + firstName);
//        System.out.println(" Lastname : " + lastName);
//        System.out.println(" Thirdname : " + thirdName);

//        return new Unicorn.UnicornBuilder(firstName, lastName, thirdName).build();
        return new Unicorn.UnicornBuilder("a", "a", "a").build();
    }

//    private static void updateDisciplineStatus(Session session) {
//        StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UpdateDiscipline");
//        storedProcedure.registerStoredProcedureParameter("firstName", String.class, ParameterMode.IN);
//        storedProcedure.setParameter("firstName", "Senaste");
//
//        storedProcedure.execute();
//        System.out.println(storedProcedure.getSingleResult());
//    }
//
//    private static List<UnicornClass> setupClasses() {
//        List<UnicornClass> unicornClasses = new ArrayList<>();
//        unicornClasses.add(new UnicornClass.UnicornClassBuilder("Biologi", new Date('2')).build());
//        unicornClasses.add(new UnicornClass.UnicornClassBuilder("It", new Date('3')).build());
//        return unicornClasses;
//    }
//
//    private static List<Unicorn> setupUnicorns() {
//        List<Unicorn> unicornClasses = new ArrayList<>();
//        unicornClasses.add(new Unicorn.UnicornBuilder("Stefan", "Stefansson", "Steffe", 1).build());
//        unicornClasses.add(new Unicorn.UnicornBuilder("Börje", "Börjesson", "Börje", 1).build());
//        return unicornClasses;
//    }
//
//    private static Unicorn setUnicornInClass(Unicorn unicorn, UnicornClass unicornClass) {
//        unicorn.getUnicornClass().add(unicornClass);
//        return unicorn;
//    }
//
//    private static UnicornClass setClassWithUnicorn(UnicornClass unicornClass, Unicorn unicorn) {
//        unicornClass.getUnicorns().add(unicorn);
//        return unicornClass;
//    }
}