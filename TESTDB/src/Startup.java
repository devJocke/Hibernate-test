import data.*;
import data.Unicorn;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.io.Console;
import java.util.*;

public class Startup {

    public static void main(String[] args) {


        /**
         * 1. First time start, create new unicorn
         * Check - Ask for Unicorn properties fname(not null),lname,tname
         * Check - Tell user that she needs to attend the unicorn and do the following
         * Play, Discipline, Flush
         *
         * Check - -- New unicorn -> Care -> disc, play, flush
         * IMPROVE -- unicorn.getcare() -> Fetch all and check which values that are 0
         *
         *
         * 2. Second start, unicorn is already created
         * Do a getCheck to see if any values are 0
         * --> Update attended property and somehow set a timer that will change the value to 0 <--
         *     ^Allow the user to take care of the unicorn, display ui for each need^
         *
         * 3. Give attributes to unicorns
         *
         * 4. Tests
         *
         */

        Session session = setupSessionConfiguration();

        try {

            System.out.println("Welcome to the unicornigotchi! ");
            System.out.println("Please make a selection ");
            System.out.println("1. Create new unicorn ");
            System.out.println("2. View existing one ");
            System.out.print("Make selection : ");
            Scanner scanner = new Scanner(System.in);
            int menuSelection = scanner.nextInt();

            switch (menuSelection){
                case 1:
                    System.out.println("----------------------");
                    System.out.println("Creating new Unicorn");
                    System.out.println("----------------------");

                    session.beginTransaction();
                    Unicorn newUnicorn = createNewUnicornFromUserInput();
                    session.save(newUnicorn);
                    session.getTransaction().commit();

                    System.out.println("\\\n" +
                            " \\ji\n" +
                            " /.((( \n" +
                            "(,/\"(((__,--.\n" +
                            "    \\  ) _( /{ \n" +
                            "    !|| \" :||      \n" +
                            "    !||   :|| \n" +
                            "    '''   ''' ");
                    break;
                case 2:
                    //TODO VIEW EXISTING UNICORN
                    break;
            }
            seeMenu(session);

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        session.close();
    }

    private static void seeMenu(Session session) {

        System.out.println("----------------------");
        System.out.println("1. Check Börjes status ");
        System.out.println("2. See beautiful Börje ");
        System.out.println("----------------------");
        System.out.print("Make selection : ");
        Scanner scanner = new Scanner(System.in);
        int menuSelection = scanner.nextInt();

        switch (menuSelection) {
            case 1:
                for (Map.Entry<String, Boolean> stringBooleanEntry : Needs.getAllUnicornNeeds(session).entrySet()) {
                    System.out.println(stringBooleanEntry.getKey());
                }
                break;
            case 2:
                System.out.println("\\\n" +
                        " \\ji\n" +
                        " /.((( \n" +
                        "(,/\"(((__,--.\n" +
                        "    \\  ) _( /{ \n" +
                        "    !|| \" :||      \n" +
                        "    !||   :|| \n" +
                        "    '''   ''' ");
                break;
        }
        seeMenu(session);
    }

    private static Unicorn selectUnicorn(Session session) {
        TypedQuery<Unicorn> unicornsTypedQuery = session.createNativeQuery("select * from Unicorn", Unicorn.class);
        System.out.println("Choose a unicorn by entering its number ");
        for (Unicorn unicorn : unicornsTypedQuery.getResultList()) {
            System.out.println(unicorn.getId() + " " + unicorn.getFirstName());
        }
        System.out.println("Choose a unicorn by entering its number ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number: ");
        int unicornId = scanner.nextInt();
        TypedQuery<Unicorn> unicornTypedQuery = session.createNativeQuery("select * from Unicorn where id = " + unicornId + "", Unicorn.class);
        Unicorn unicorn = unicornTypedQuery.getSingleResult();
        System.out.println("You have selected : " + unicorn.getFirstName());
        return unicorn;

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

    private static Unicorn createNewUnicornFromUserInput() {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter unicorn firstname: ");
        String firstName = userInput.next();

        System.out.print("Enter unicorn lastname: ");
        String lastName = userInput.next();

        System.out.print("Enter unicorn thirdname: ");
        String thirdName = userInput.next();

        System.out.println(" A new Unicorn has been created, take good care of him!");
        System.out.println(" Firstname : " + firstName);
        System.out.println(" Lastname : " + lastName);
        System.out.println(" Thirdname : " + thirdName);

        return new Unicorn.UnicornBuilder(firstName, lastName, thirdName).build();
//        return new Unicorn.UnicornBuilder("Börje", "Börjesson", "Bjorn").build();
    }

//    private static void updateDisciplineStatus(Session session) {
//        StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UpdateDiscipline");
//        storedProcedure.registerStoredProcedureParameter("firstName", String.class, ParameterMode.IN);
//        storedProcedure.setParameter("firstName", "Senaste");
//
//        storedProcedure.execute();
//        System.out.println(storedProcedure.getSingleResult());
//    }
}