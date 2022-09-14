package Ingredients;

import javax.sound.midi.Soundbank;
import java.util.Locale;
import java.util.Scanner;

public abstract class Navigation {

    private static boolean isOver = false;
    private static Scanner sc = new Scanner(System.in);

    private static int takeInt() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.print("Input is not valid, try again: ");
            scan.nextLine();
        }
        return scan.nextInt();
    }

    private static double takeDouble() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextDouble()) {
            System.out.print("Input is not valid, try again: ");
            scan.nextLine();
        }
        return scan.nextDouble();
    }

    public static void mainMenu() {
        DataBase.initialiseDB();

        while (!isOver) {
            System.out.println("\n".repeat(50));
            System.out.println("*******WELCOME TO THE KICHEN*******\n".toUpperCase());
            System.out.println("1: Menu");
            System.out.println("2: Favorite dishes");
            System.out.println("3: Fridge");
            System.out.println("0: Quit");

            int choice = takeInt();

            switch (choice) {
                case 1: {
                    Navigation.foodMenu();
                    break;
                }
                case 2: {
                    Navigation.favorites();
                    break;
                }
                case 3: {
                    Navigation.fridge();
                    break;
                }
                case 0: {
                    Navigation.isOver = true;
                    return;
                }
                default: {
                    System.out.print("Input is not valid, try again: ");
                }
            }
        }
    }

    static void foodMenu() {
        System.out.println("\n".repeat(50));
        while (true) {
            System.out.println("\n \n *******FOOD MENU*******\n\n");
            System.out.println(DataBase.writeRecipe());


            System.out.println("1: Make dish");
            System.out.println("2: Possible dishes");
            System.out.println("3: Filter");
            System.out.println("4: Sort");
            System.out.println("0: Back");
            System.out.print("Choose an option: ");
            int choice = takeInt();


            switch (choice) {
                case 1: {
                    Navigation.makeDish();
                    break;
                }
                case 2: {
                    Navigation.possibleRecipes();
                    break;
                }
                case 3: {
                    System.out.print("Insert max price(0 = no filter): ");
                    double price = takeDouble();
                    System.out.println("0: NO FILTER 1: BEGINNER 2: EASY 3: MEDIUM 4: HARD 5: PRO");
                    System.out.print("Insert complexity: ");
                    int complexity = takeInt();
                    while (complexity > Complexity.values().length - 1 || complexity < 0) {
                        System.out.print("Input is not valid, try again: ");
                        complexity = takeInt();
                    }

                    Navigation.dishFilter(price, complexity - 1);
                    break;
                }
                case 4: {
                    System.out.println("\n".repeat(50));
                    System.out.print("Coming soon. Insert anything to go back: ");
                    sc.nextLine();
                    break;
                }
                case 0: {

                    return;
                }
                default: {
                    System.out.println("Input is not valid, try again: ");
                }

            }
        }
    }

    static void dishFilter(double price, int complexity) {
        if (complexity < 0 && price > 0) {
            System.out.println("\n".repeat(50));
            System.out.println("\n \n Dishes below " + price + " RSD ");
            System.out.println(DataBase.writePossibleForPrice(price));
        } else if (price <= 0 && complexity >= 0) {
            System.out.println("\n".repeat(50));
            System.out.println("\n \n Dishes with " + Complexity.values()[complexity] + " complexity");
            System.out.println(DataBase.writeByComplexity(complexity));
        } else {

            System.out.println("\n".repeat(50));
            System.out.println("\n \n Dishes with " + Complexity.values()[complexity] + " complexity and below " + price + " RSD: ");
            System.out.println(DataBase.writeByComplexityandPrice(price, complexity));
        }


        System.out.println("1: Make dish");
        System.out.println("0: Back");

        int choice = takeInt();


        switch (choice) {
            case 1: {
                Navigation.makeDish();
                break;
            }
            case 0: {

                return;
            }
            default: {
                System.out.print("Input is not valid, try again: ");
            }

        }

    }

    static void addToFav() {
        System.out.println("\n".repeat(50));
        System.out.println(DataBase.writeRecipe());
        System.out.print("Insert dish id: ");
        int choice = takeInt();
        while (!DataBase.getRecipeHM().containsKey(choice)) {
            System.out.print("Input is not valid, try again: ");
            choice = takeInt();
        }
        DataBase.getRecipeHM().get(choice).setFav(true);
    }

    static void removeFromFav() {
        System.out.println(DataBase.writeFav());
        System.out.print("Insert dish id: ");
        int choice = takeInt();
        while (!DataBase.getRecipeHM().containsKey(choice)) {
            System.out.print("Input is not valid, try again: ");
            choice = takeInt();
        }
        DataBase.getRecipeHM().get(choice).setFav(false);
    }


    static void favorites() {

        while (true) {
            System.out.println("\n".repeat(50));
            System.out.println("\n \n*******FAVORITE DISHES*******");
            System.out.println(DataBase.writeFav());
            System.out.println("1: Add to favorites");
            System.out.println("2: Remove from favorites");
            System.out.println("0: Back");
            System.out.print("Choose an option: ");
            int choice = takeInt();

            switch (choice) {
                case 1: {
                    addToFav();
                    break;
                }
                case 2: {
                    removeFromFav();
                    break;
                }
                case 0: {
                    return;
                }
                default:
                    System.out.println("Input is invalid, try again: ");
            }
        }
    }

    static void makeDish() {
        System.out.println("\n".repeat(50));
        System.out.println(DataBase.writeRecipe());
        System.out.println("0: Back\n");
        System.out.print("Insert dish ID: ");
        int id = takeInt();
        if (id == 0) {
            return;
        }


        while (!DataBase.getRecipeHM().containsKey(id)) {
            System.out.print("Input is not valid, try again: ");
            id = takeInt();
            if (id == 0) {
                return;
            }

        }
        if (Fridge.recipePossible(DataBase.getRecipeHM().get(id))) {
            Fridge.make(DataBase.getRecipeHM().get(id));
            System.out.println("The dish has been made!");
            return;
        }
        System.out.println("Not enough ingredients.");

    }

    static void makeScaledDish() {
        System.out.println("\n".repeat(50));
        System.out.println(DataBase.writeRecipe());

        System.out.println("0: Back\n");
        System.out.print("Insert dish ID: ");
        int id = takeInt();
        if (id == 0) {
            return;
        }


        while (!DataBase.getRecipeHM().containsKey(id)) {
            System.out.print("Input is not valid, try again: ");
            id = takeInt();
            if (id == 0) {
                return;
            }
        }


        if (Fridge.recipePossibleScaled(DataBase.getRecipeHM().get(id), 50)) {
            Fridge.make(DataBase.getRecipeHM().get(id).getScaledRecipe(50));
            System.out.println("The dish has been made!");
            return;
        }
        System.out.println("Not enough groceries.");

    }


    static void possibleRecipes() {
        System.out.println("\n".repeat(50));
        System.out.println("\n".repeat(50));
        System.out.println("*******POSSIBLE DISHES*******\n");
        System.out.println(DataBase.writePossible());


        System.out.println("1: Make dish");
        System.out.println("2: Possible half portion");
        System.out.println("0: Back");
        int choice = takeInt();


        switch (choice) {
            case 1: {
                Navigation.makeDish();
                break;
            }
            case 2: {
                Navigation.possibleScaledRecipes();
                break;
            }
            case 0: {

                return;
            }
            default: {
                System.out.println("Input is not valid, try again: ");
            }

        }
    }

    static void possibleScaledRecipes() {
        System.out.println("\n".repeat(50));
        System.out.println("\n \n *******POSSIBLE HALF PORTION*******\n");
        System.out.println(DataBase.writePossibleScaled(50));
        System.out.println("1: Make half portion");
        System.out.println("0: Back");
        int choice = takeInt();

        switch (choice) {
            case 1: {
                Navigation.makeScaledDish();
                break;
            }
            case 0: {

                return;
            }
            default: {
                System.out.println("Input is not valid, try again: ");
            }

        }


    }

    private static void fridge() {
        System.out.println("\n".repeat(50));
        while (true) {
            System.out.println("\n".repeat(3));
            System.out.println(Fridge.lookInFridge());

            System.out.println("1: Add ingredients");
            System.out.println("2: Remove ingredients");
            System.out.println("0: Back");
            System.out.print("Choose an option: ");
            int choice = takeInt();

            switch (choice) {
                case 1: {
                    Navigation.addintoFridge();
                    break;
                }
                case 2: {
                    Navigation.removeFromFridge();
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("Input is not valid, try again: ");
                }
            }
        }
    }

    private static void addintoFridge() {
        System.out.println("\n".repeat(50));
        System.out.println("\n".repeat(3));
        System.out.println("\n" + DataBase.writeIng());
        System.out.println("0: Back");
        System.out.print("Insert ingredient ID: ");
        int id = takeInt();
        if (id == 0) {
            return;
        }
        while (!DataBase.getIngHM().containsKey(id)) {
            System.out.print("Insert ingredient ID: ");
            id = sc.nextInt();
            if (id == 0) {
                return;
            }
        }
        System.out.print("Insert weight to be added: ");
        double weight = takeDouble();
        Fridge.addToFridge(DataBase.getIngHM().get(id), weight);

    }

    private static void removeFromFridge() {
        System.out.println("\n".repeat(50));
        System.out.println("\n".repeat(3));
        System.out.println("\n" + DataBase.writeIng());
        System.out.println("0: Back");
        System.out.print("Insert ingredient ID to remove: ");
        int id = takeInt();
        if (id == 0) {
            return;
        }
        while (!DataBase.getIngHM().containsKey(id)) {
            System.out.print("Input is not valid, try again: ");
            id = takeInt();
            if (id == 0) {
                return;
            }
        }
        System.out.print("Insert quantity to remove (0 to remove completely): ");
        double weight = takeDouble();
        if (weight != 0 && weight < DataBase.getIngHM().get(id).getWeight()) {
            Fridge.addToFridge(DataBase.getIngHM().get(id), -weight);
            return;
        }
        Fridge.removeFromFridge(DataBase.getIngHM().get(id));
    }

}
