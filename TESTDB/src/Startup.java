import Dal.AccessUnicorn;
import Data.Care;
import Data.Unicorn;
import org.hibernate.*;

import javax.persistence.TypedQuery;
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
         * xx. Tests
         *
         */


        try {

//            viewMainMenu();
            Unicorn unicorn = AccessUnicorn.getAllUnicorns();
            viewUnicornMenu(unicorn);
            viewUpdateMenu(unicorn);

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static void viewMainMenu() {
        System.out.println("Welcome to the Unicornigotchi! ");
        System.out.println("Please make a selection ");
        System.out.println("1. Create new unicorn ");
        System.out.println("2. View existing one ");
        System.out.print("Make selection : ");
        Scanner scanner = new Scanner(System.in);
        int menuSelection = scanner.nextInt();

        switch (menuSelection) {
            case 1:
                System.out.println("----------------------");
                System.out.println("Creating new Unicorn");
                System.out.println("----------------------");


                Unicorn newUnicorn = createNewUnicornFromUserInput();
                AccessUnicorn.createUnicorn(newUnicorn);

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
    }

    private static void viewUpdateMenu(Unicorn unicorn) {

        ArrayList<Care.CareInformation> allCategoriesInNeed = unicorn.getCare().getAllCategoriesInNeed();

        System.out.println("----------------------");
        for (int i = 0; i < allCategoriesInNeed.size(); i++) {
            System.out.println(i + ". " + allCategoriesInNeed.get(i));
        }

        System.out.print("Make selection : ");
        Scanner in = new Scanner(System.in);
        int selectedCategory = in.nextInt();
        for (int i = 0; i < allCategoriesInNeed.size(); i++) {
            if (selectedCategory == i) {
                Care.CareInformation category = allCategoriesInNeed.get(i);
                System.out.println("You selected " + category);
                showAllSubCategories(category, i);
                System.out.println("Select an option to make " + unicorn.getFirstName() + " happier!");
                System.out.println("Please select : ");

                Scanner subCategoryIndex = new Scanner(System.in);
                int selectedSubCategory = subCategoryIndex.nextInt();
                updateSubCategories(unicorn, category, selectedSubCategory);
            }
        }
    }

    private static void showAllSubCategories(Care.CareInformation unicornCareInformations, int categoryIndex) {

        Set<Map.Entry<String, Boolean>> categories = unicornCareInformations.getCategory().entrySet();
        for (Map.Entry<String, Boolean> subCategories : categories) {
            String capitalizedWord = subCategories.getKey().substring(0, 1).toUpperCase() + subCategories.getKey().substring(1).toLowerCase();
            System.out.println(categoryIndex + ". " + capitalizedWord + " : " + subCategories.getValue());
        }
    }

    private static void updateSubCategories(Unicorn unicorn, Care.CareInformation categoryToUpdate, int selectedCategory) {

        int index = 0;
        for (Map.Entry<String, Boolean> catergoryEntries : categoryToUpdate.getCategory().entrySet()) {
            if (index == selectedCategory) {
                categoryToUpdate.getCategory().put(catergoryEntries.getKey(), false);
                categoryToUpdate.save(catergoryEntries.getKey());
            }
            index++;
        }

        AccessUnicorn.updateNeeds(unicorn);
    }


    private static void viewUnicornMenu(Unicorn unicorn) {

        System.out.println("----------------------");
        System.out.println("1. Check " + unicorn.getFirstName() + " status ");
        System.out.println("2. See beautiful " + unicorn.getFirstName() + "  ");
        System.out.println("----------------------");
        System.out.print("Make selection : ");
        Scanner scanner = new Scanner(System.in);
        int menuSelection = scanner.nextInt();

        switch (menuSelection) {
            case 1:
                for (Care.CareInformation allCategoriesInNeed : unicorn.getCare().getAllCategoriesInNeed()) {
                    System.out.println(allCategoriesInNeed.getCategory().entrySet());
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
//        viewUnicornMenu();
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

    private static Unicorn createNewUnicornFromUserInput() {
//        Scanner userInput = new Scanner(System.in);
//
//        System.out.print("Enter unicorn firstname: ");
//        String firstName = userInput.next();
//
//        System.out.print("Enter unicorn lastname: ");
//        String lastName = userInput.next();
//
//        System.out.print("Enter unicorn thirdname: ");
//        String thirdName = userInput.next();
//
//        System.out.println(" A new Unicorn has been created, take good care of him!");
//        System.out.println(" Firstname : " + firstName);
//        System.out.println(" Lastname : " + lastName);
//        System.out.println(" Thirdname : " + thirdName);
//
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
}