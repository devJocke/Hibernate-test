import data.*;
import data.Unicorn;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;

import javax.persistence.TypedQuery;
import java.util.*;

public class Startup {

    public static void main(String[] args) {


        /**
         * 1. First time start, create new unicorn
         * Check - Ask for Unicorn properties fname(not null),lname,tname
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
         *
         *
         *
         */

        Session session = setupSessionConfiguration();

        try {

            session.beginTransaction();
//            Unicorn newUnicorn = createNewUnicornFromUserInput();
//            session.save(newUnicorn);

            System.out.print("\\\n" +
                    " \\ji\n" +
                    " /.((( \n" +
                    "(,/\"(((__,--.\n" +
                    "    \\  ) _( /{ \n" +
                    "    !|| \" :||      \n" +
                    "    !||   :|| \n" +
                    "    '''   ''' ");

            TypedQuery<Unicorn> unicornTypedQuery = session.createNativeQuery("select * from Unicorn", Unicorn.class);

//            TypedQuery<Care> care = session.createNativeQuery("select * from Care \n" +
//                    "inner join Unicorn U on C.id = U.unicornCareId", Care.class);

            Unicorn unicorn =unicornTypedQuery.getSingleResult();
             Care care = unicorn.getCare();
            care.getFlush();
            care.getPlay();
            if(care.getDiscipline().getMood().equals("Default")){
                System.out.println(unicorn.getFirstName() + " is " + care.getDiscipline().getMood());
            }

//            TypedQuery<Discipline> discipline = session.createNativeQuery("select * from Discipline \n" +
//                    "inner join Care C on C.id = Discipline.id\n" +
//                    "inner join Unicorn U on C.id = U.unicornCareId", Discipline.class);

//            System.out.print(discipline.getSingleResult().getMood());

//            TypedQuery<Unicorn> unicornTypedQuery = session.createNativeQuery("select * from Unicorn", Unicorn.class);
//            Unicorn unicorn = unicornTypedQuery.getSingleResult();

//            System.out.println("This is your new unicorn, meet " + unicorn + "");


//            List<UnicornClass> unicornClasses;
//            TypedQuery<UnicornClass> discipline = session.createNativeQuery("select * from UnicornClass  where UnicornClass.subject = 'biologi'", UnicornClass.class);
//
//            session.beginTransaction();
//
//            List<Unicorn> unicorns = setupUnicorns();
//
//
//            if (discipline.getResultList().isEmpty()) {
//                unicornClasses = setupClasses();
//                for (UnicornClass unicornClass1 : unicornClasses) {
//                    for (Unicorn unicorn : unicorns) {
//                        session.save(setClassWithUnicorn(unicornClass1, unicorn));
//                        session.save(setUnicornInClass(unicorn, unicornClass1));
//                    }
//                }
//            } else {
//                unicornClasses = discipline.getResultList();
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
        return new Unicorn.UnicornBuilder("Börje", "Börjesson", "Bjorn").build();
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