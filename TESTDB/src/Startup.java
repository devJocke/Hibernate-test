import Dal.AccessUnicorn;
import Dao.Care;
import Dao.Unicorn;
import org.apache.log4j.BasicConfigurator;
import org.hibernate.*;

import javax.persistence.TypedQuery;
import java.util.*;
import java.util.logging.Level;

public class Startup {

    public static void main(String[] args) {

        /**
         * 0. First time start, create new unicorn
         * Check - Ask for Unicorn properties fname(not null),lname,tname
         * Check - Tell user that she needs to attend the unicorn and do the following
         * Play, Discipline, Toilet
         *
         * Check - -- New unicorn -> Care -> disc, play, flush
         * IMPROVE -- unicorn.getcare() -> Fetch all and check which values that are 0
         *
         *
         * 1. Second start, unicorn is already created
         * Do a getCheck to see if any values are 0
         * --> Update attended property and somehow set a timer that will change the value to 0 <--
         *        ^Allow the user to take care of the unicorn, display ui for each need^
         *
         * 2. Give attributes to unicorns
         * 3. Have a farm with actual minigames
         * 3.1 Fishingschack - Fish - Clean up?
         * 3.2 Pet battles?
         * 3.3 Cleaning?
         *
         * ----------------
         * 4. Give attributes to unicorns
         * 5  Levelup?
         * 6. Enemies?
         * 7. Recruit?
         *
         * xx. Tests
         *
         * If more users are on the same account race conditions occurs
         *
         */
        Startup startup = new Startup();
        try {
            BasicConfigurator.configure();
            System.out.println("Fetching data....");
            Unicorn unicorn = AccessUnicorn.preLoadUnicorns();

            if (unicorn.getFirstName() != null) {
                startup.viewMainMenu(unicorn);
            } else {
                System.out.println("No unicorn found lets create a new one");
                Unicorn newUnicorn = startup.createNewUnicornFromUserInput();
                AccessUnicorn.createUnicorn(newUnicorn);
                startup.viewMainMenu(newUnicorn);
                System.out.println(unicorn.getFarm());
                //FETCH CREATED  DATA
            }
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private void viewMainMenu(Unicorn unicorn) {
        System.out.println("Welcome to the Unicornigotchi! ");
        System.out.println("Please make a selection ");
        System.out.println("1. Create new unicorn - DONT DO THIS YET");
        System.out.println("2. View existing one ");
        System.out.print("Make selection : ");
//        viewUnicornMenu(unicorn);
        try {
            Scanner scanner = new Scanner(System.in);
            int menuSelection = scanner.nextInt();
            if (menuSelection > 0 && menuSelection < 3) {
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

                        viewMainMenu(newUnicorn);
                        break;
                    case 2:
                        //TODO LOAD UNICORN ON UNICORN ID
//                Unicorn unicorn = AccessUnicorn.getUnicorn();
                        viewUnicornMenu(unicorn);
                        break;
                }
            } else {
                viewMainMenu(unicorn);
            }
        } catch (InputMismatchException imp) {
            System.err.println("Invalid input provided, use numerics only 1-9");
            viewMainMenu(unicorn);
        }
    }


    private void viewUnicornMenu(Unicorn unicorn) {

        AccessUnicorn.checkForUpdatedData(unicorn);

        if (unicorn.getCare().getNeeds().isEmpty()) {
            System.out.println("Nothing needs to be done, check back later");
            viewMainMenu(unicorn);
            return;
        }

        System.out.println("------ UNICORN MENU ------");
        System.out.println("0. Main menu");
        System.out.println("1. Check " + unicorn.getFirstName() + " status ");
        System.out.println("2. See beautiful " + unicorn.getFirstName());
        System.out.println("3. Update " + unicorn.getFirstName());
        System.out.println("4. See " + unicorn.getFirstName() + "s farm");
        System.out.println("--------------------------");
        System.out.print("Make selection : ");

        try {
            Scanner scanner = new Scanner(System.in);
            int menuSelection = scanner.nextInt();

            if (menuSelection > 0 && menuSelection < 5) {
                switch (menuSelection) {
                    case 1:
                        System.out.println("\n" + unicorn.getFirstName() + " needs some attention");
                        System.out.println(unicorn.getCare().getNeeds() + "\n");
                        break;
                    case 2:
                        System.out.println("\t\\\n" +
                                "\t \\ji\n" +
                                "\t /.((( \n" +
                                "\t(,/\"(((__,--.\n" +
                                "\t    \\  ) _( /{ \n" +
                                "\t    !|| \" :||      \n" +
                                "\t    !||   :|| \n" +
                                "\t    '''   ''' ");
                        break;
                    case 3:
                        viewUpdateMenu(unicorn);
                        return;
                    case 4:
                        System.err.println(unicorn.getFirstName() + " does not have any farm yet");
//                        unicorn.getFarm().getFarmOverView();
                        break;
                }
            } else {
                viewMainMenu(unicorn);
            }
            viewUnicornMenu(unicorn);
        } catch (InputMismatchException imp) {
            System.err.println("Invalid input provided, use numerics only 1-9");
            viewUnicornMenu(unicorn);
        }
    }

    private Unicorn createNewUnicornFromUserInput() {

        try {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Enter unicorn firstname: ");
            String firstName = userInput.next();
            String upperCaseFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
            System.out.print("Enter unicorn lastname: ");
            String lastName = userInput.next();

            System.out.print("Enter unicorn thirdname: ");
            String thirdName = userInput.next();

            System.out.println(" A new Unicorn has been created, take good care of him!");
            System.out.println(" Meet " + upperCaseFirstName + " , " + lastName + " , " + thirdName);
            return new Unicorn.UnicornBuilder(upperCaseFirstName, lastName, thirdName).build();
        } catch (InputMismatchException imp) {
            System.err.println("Invalid input provided, use numerics only 1-9");
            createNewUnicornFromUserInput();
        }
        return null;
    }

    /**
     * Displays and handles all the input from the user
     *
     * @param unicorn to be used
     */
    private void viewUpdateMenu(Unicorn unicorn) {

        //MUST BE FALSE

        AccessUnicorn.checkForUpdatedData(unicorn);
        List<Care.CareInformation> allCategoriesInNeed = unicorn.getCare().getNeeds();
        System.out.println("------ UPDATE MENU ------");
        System.out.println("0. Go back");
        for (int i = 0; i < allCategoriesInNeed.size(); i++) {
            System.out.println(i + 1 + ". " + allCategoriesInNeed.get(i));
        }

        try {
            System.out.print("Make selection : ");
            Scanner in = new Scanner(System.in);
            int selectedCategory = in.nextInt() - 1;
            if (isCollectionOutsideCategoryRange(selectedCategory, allCategoriesInNeed.size())) {
                viewUnicornMenu(unicorn);
                return;
            }
            Care.CareInformation category = allCategoriesInNeed.get(selectedCategory);
            System.out.println("You selected " + category);
            selectSubCategory(unicorn, allCategoriesInNeed.get(selectedCategory));

        } catch (InputMismatchException imp) {
            System.err.println("Invalid input provided, use numerics only 1-9");
            viewUpdateMenu(unicorn);
        }
        viewUnicornMenu(unicorn);
    }

    private void selectSubCategory(Unicorn unicorn, Care.CareInformation careInformation) {
        //DISPLAY ALL SUB CATEGORIES
        System.out.println("0. Go back");
        showAllSubCategories(careInformation);
        System.out.println("Select an option to make " + unicorn.getFirstName() + " happier!");
        System.out.print("Please select : ");

        Scanner subCategoryIndex = new Scanner(System.in);
        int selectedSubCategory = subCategoryIndex.nextInt() - 1;
        if (isCollectionOutsideCategoryRange(selectedSubCategory, careInformation.getCategories().size())) {
            viewUpdateMenu(unicorn);
        } else if (selectedSubCategory <= careInformation.getCategories().size()) {
            AccessUnicorn.checkForUpdatedData(unicorn);
            updateSubCategories(unicorn, careInformation, selectedSubCategory);
        }
    }

    private void showAllSubCategories(Care.CareInformation unicornCareInformations) {
        int index = 0;
        Set<Map.Entry<String, Boolean>> categories = unicornCareInformations.getCategories().entrySet();
        for (Map.Entry<String, Boolean> subCategories : categories) {
            String capitalizedWord = subCategories.getKey().substring(0, 1).toUpperCase() + subCategories.getKey().substring(1).toLowerCase();
            System.out.println(index + 1 + ". " + capitalizedWord);
            index++;
        }

    }

    private boolean isCollectionOutsideCategoryRange(int selectedCategory, int size) {
        return selectedCategory < 0 || selectedCategory >= size;
    }

    private void updateSubCategories(Unicorn unicorn, Care.CareInformation categoryToUpdate, int selectedCategory) {

        if (selectedCategory < categoryToUpdate.getCategories().size()) {
            String key = categoryToUpdate.save(getElementByIndex(categoryToUpdate.getCategories(), selectedCategory));
            System.out.println("-------------------------");
            System.out.println(unicorn.getFirstName() + " " + key);
            AccessUnicorn.updateUnicornNeeds(unicorn);
        }
    }

    private String getElementByIndex(Map<String, Boolean> map, int index) {
        return ((Map.Entry<String, Boolean>) map.entrySet().toArray()[index]).getKey();
    }

    private Unicorn selectUnicorn(Session session) {
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

//    private static void updateDisciplineStatus(Session session) {
//        StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UpdateDiscipline");
//        storedProcedure.registerStoredProcedureParameter("firstName", String.class, ParameterMode.IN);
//        storedProcedure.setParameter("firstName", "Senaste");
//
//        storedProcedure.execute();
//        System.out.println(storedProcedure.getSingleResult());
//    }
}