package Ingredients;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public abstract class Navigation {

    private static boolean isOver = false;
    private static Scanner sc = new Scanner(System.in);
    public static void mainMenu() {
        DataBase.initialiseDB();

        while (!isOver) {
            System.out.println("\n".repeat(50));
            System.out.println("*******Dobrodosli u kuhinju*******".toUpperCase());
            System.out.println("1: Meni");
            System.out.println("2: Omiljeni recepti");
            System.out.println("3: Frizider");
            System.out.println("0: Izlaz");

            String choice = sc.next();
            sc.nextLine();

            switch (choice) {
                case "1": {
                    Navigation.foodMenu();
                    break;
                }
                case "2": {

                }
                case "3": {
                    Navigation.fridge();
                    break;
                }
                case "0": {
                    Navigation.isOver = true;
                    return;
                }
                default:{
                    System.out.println("unesite ispravan broj");
                }
            }

        }
    }
    static void foodMenu() {
        System.out.println("\n".repeat(50));
        while (true) {
            System.out.println("\n \n *******FOOD MENU*******\n\n");
            System.out.println(DataBase.writeRecipe());


            System.out.println("1: Napravi jelo");
            System.out.println("2: Moguca jela sa trenutnim namirnicama");
            System.out.println("3: Filtriranje jela");
            System.out.println("4: Sortiranje jela");
            System.out.println("0: Nazad");
            System.out.print("Izaberite opciju: ");
            String choice = sc.next();
            sc.nextLine();

            switch (choice) {
                case "1": {
                    Navigation.makeDish();
                    break;
                }
                case "2": {
                    Navigation.possibleRecipes();
                    break;
                }
                case "3": {
                    System.out.print("Unesite maksimalnu cenu (0 ako ne filtrirate po ceni): ");
                    double price = sc.nextDouble();
                    System.out.println("0: BEZ FILTERA 1: BEGINNER, 2: EASY, 3: MEDIUM, 4: HARD, 5: PRO");
                    System.out.print("Unesite tezinu: ");
                    int complexity = (int)sc.nextDouble();

                    Navigation.dishFilter(price,complexity-1);
                    break;
                }
                case "4": {
                    break;
                }
                case "0": {

                    return;
                }
                default: {
                    System.out.println("unesite ispravan broj");
                }

            }
        }
    }
    static void dishFilter(double price, int complexity){
        if(complexity < 0 && price > 0) {
            System.out.println("\n".repeat(50));
            System.out.println("\n \n Jela do " + price + " dinara: ");
            System.out.println(DataBase.writePossibleForPrice(price));
        } else if (price<=0 && complexity >= 0){
            System.out.println("\n".repeat(50));
            System.out.println("\n \n Jela tezine " + Complexity.values()[complexity]);
            System.out.println(DataBase.writeByComplexity(complexity));
        }else{

                System.out.println("\n".repeat(50));
                System.out.println("\n \n Jela tezine " + Complexity.values()[complexity] + " i cene do " + price + ": ");
                System.out.println(DataBase.writeByComplexityandPrice(price,complexity));
        }


        System.out.println("1: Napravi jelo");
        System.out.println("0: Nazad");

        String choice = sc.next();
        sc.nextLine();

        switch (choice) {
            case "1": {
                Navigation.makeDish();
                break;
            }
            case "0": {

                return;
            }
            default: {
                System.out.print("unesite ispravan broj: ");
            }

        }

    }

    static void favorites(){

    }

    static void makeDish(){

        System.out.print("\n Unesite naziv jela koje zelite da napravite: ");
        String name = sc.nextLine().trim().toUpperCase(Locale.ROOT);

        while (!DataBase.hasRecipe(name)){
            System.out.print("Neispravan naziv jela, pokusajte ponovo: ");
            name = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        }
        if(Fridge.recipePossible(DataBase.getRecipe(name))){
            Fridge.make(DataBase.getRecipe(name));
            System.out.println("Jelo je napravljeno!");
            return;
        }
        System.out.println("Nemate dovoljno namirnica.");

    }
    static void makeScaledDish(){

        System.out.print("\n Unesite naziv jela koje zelite da napravite: ");
        String name = sc.nextLine().trim().toUpperCase(Locale.ROOT);

        while (!DataBase.hasRecipe(name)){
            System.out.print("Neispravan naziv jela, pokusajte ponovo: ");
            name = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        }
        if(Fridge.recipePossibleScaled(DataBase.getRecipe(name),50)){
            Fridge.make(DataBase.getRecipe(name).getScaledRecipe(50));
            System.out.println("Jelo je napravljeno!");
            return;
        }
        System.out.println("Nemate dovoljno namirnica.");

    }

    static void possibleRecipes(){
        System.out.println("\n".repeat(50));
        System.out.println("\n".repeat(50));
        System.out.println("*******MOGUCA JELA*******\n");
        System.out.println(DataBase.writePossible());


        System.out.println("1: Napravi jelo");
        System.out.println("2: Moguca jela za pola porcije");
        System.out.println("0: Nazad");
        String choice = sc.next();
        sc.nextLine();

        switch (choice) {
            case "1": {
                Navigation.makeDish();
                break;
            }
            case "2": {
                Navigation.possibleScaledRecipes();
                break;
            }
            case "0": {

                return;
            }
            default: {
                System.out.println("unesite ispravan broj");
            }

        }
    }
        static void possibleScaledRecipes(){
            System.out.println("\n".repeat(50));
            System.out.println("\n \n *******MOGUCA JELA POLA PORCIJE*******\n");
            System.out.println(DataBase.writePossibleScaled(50));
            System.out.println("1: Napravi pola porcije");
            System.out.println("0: Nazad");
            String choice = sc.next();
            sc.nextLine();

            switch (choice) {
                case "1": {
                    Navigation.makeScaledDish();
                    break;
                }
                case "0": {

                    return;
                }
                default: {
                    System.out.println("unesite ispravan broj");
                }

            }


    }

    private static  void fridge(){
        System.out.println("\n".repeat(50));
        while (true) {
            System.out.println("\n".repeat(3));
            System.out.println(Fridge.lookInFridge());

            System.out.println("1: Dodaj namirnice");
            System.out.println("2: Izbrisi namirnice");
            System.out.println("0: Nazad");
            System.out.print("Izaberite opciju: ");
            String choice = sc.next();
            sc.nextLine();

            switch (choice) {
                case "1": {
                    Navigation.addintoFridge();
                    break;
                }
                case "2": {
                    Navigation.removeFromFridge();
                    break;
                }
                case "0": {
                    return;
                }
                default:{
                    System.out.println("unesite ispravan broj");
                }
            }
        }
    }
    private static void addintoFridge(){
        System.out.println("\n".repeat(50));
        System.out.println("\n".repeat(3));
        System.out.println("\n" + DataBase.writeIng());
        System.out.print("Unesite naziv namirnice koju zelite da unesete: ");
        String name = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        while(!DataBase.hasIngredient(name)){
            System.out.print("Unesite ime sastojka sa liste: ");
            name = sc.nextLine();
        }
        System.out.print("Unesite kolicinu koju zelite da dodate: ");
        double weight = sc.nextDouble();
        Fridge.addToFridge(DataBase.getIngredient(name),weight);

    }
    private static void removeFromFridge(){
        System.out.println("\n".repeat(50));
        System.out.println("\n".repeat(3));
        System.out.println("\n" + DataBase.writeIng());
        System.out.print("Unesite naziv namirnice koju zelite da izbrisete: ");
        String name = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        while(!DataBase.hasIngredient(name)){
            System.out.print("Unesite ime sastojka sa liste");
            name = sc.nextLine();
        }
        System.out.print("Unesite kolicinu koju zelite da izbrisete(0 za izbacivanje iz frizidera): ");
        double weight = sc.nextDouble();
        if(weight!=0 && weight < DataBase.getIngredient(name).getWeight()) {
            Fridge.addToFridge(DataBase.getIngredient(name), -weight);
            return;
        }
        Fridge.removeFromFridge(DataBase.getIngredient(name));
    }

}
