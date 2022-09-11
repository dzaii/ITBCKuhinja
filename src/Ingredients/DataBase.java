package Ingredients;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

public abstract class DataBase {
    private static HashMap<String, WeightedIngredient> ingHM = new HashMap<>();
    private static HashMap<String, Recipe> recipeHM = new HashMap<>();

    static void addIngredient(WeightedIngredient ingredient) {
        ingHM.put(ingredient.getName(), ingredient);
    }

    static void addRecipe(Recipe recipe) {
        recipeHM.put(recipe.getName(), recipe);
    }

    public static void initialiseDB() {
        ingHM.put("butter", new WeightedIngredient("butter", 1000));
        ingHM.put("egg", new WeightedIngredient("egg", 16));
        ingHM.put("flour", new WeightedIngredient("flour", 60));
        ingHM.put("olive oil", new WeightedIngredient("olive oil", 800));
        ingHM.put("milk", new WeightedIngredient("milk", 130));
        ingHM.put("onion", new WeightedIngredient("onion", 60));
        ingHM.put("rice", new WeightedIngredient("rice", 80));
        ingHM.put("potato", new WeightedIngredient("potato", 50));
        ingHM.put("mustard", new WeightedIngredient("mustard", 350));
        ingHM.put("chicken", new WeightedIngredient("chicken", 450));
        ingHM.put("tomato", new WeightedIngredient("tomato", 160));
        ingHM.put("bell pepper", new WeightedIngredient("bell pepper", 180));
        ingHM.put("mushroom", new WeightedIngredient("mushroom", 200));
        ingHM.put("mozzarella", new WeightedIngredient("mozzarella", 1300));
        ingHM.put("beef", new WeightedIngredient("beef", 1100));

        Recipe omelet = new Recipe("Tomato and Green Pepper Omelet".trim().toLowerCase(Locale.ROOT), Complexity.MEDIUM);
        DataBase.addRecipe(omelet);
        omelet.addIngredient("butter", 0.05);
        omelet.addIngredient("onion", 0.07);
        omelet.addIngredient("olive oil", 0.03);
        omelet.addIngredient("egg", 3);
        omelet.addIngredient("bell pepper", 0.1);
        omelet.addIngredient("tomato", 0.1);

        for (WeightedIngredient ingredient : DataBase.ingHM.values()) {
            Fridge.addToFridge(ingredient,0.1 * ((int) (Math.random() * 100)));
        }

    }


    public static boolean hasIngredient(String name) {
        return ingHM.containsKey(name);
    }

    public static WeightedIngredient getIngredient(String name) {
        return ingHM.get(name);
    }

    public static boolean hasRecipe(String name) {
        return recipeHM.containsKey(name);
    }

    public static Recipe getRecipe(String name) {
        return recipeHM.get(name);
    }


    public static String writeIng() {
        String s = "";
        for (String name : DataBase.ingHM.keySet()) {
            s += name + "\n";
        }
        return s;
    }

    public static String writeRecipe() {
        String s = "";
        for (String name : DataBase.recipeHM.keySet()) {
            s += name + "....." + DataBase.getRecipe(name).getPrice() + " din.\n" + "       "+ DataBase.getRecipe(name).toString()+"\n\n";
        }
        return s;
    }

    public static String writePossible() {
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (Fridge.recipePossible(recipe))
                s += recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }
    public static String writePossibleForPrice(double price){
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (recipe.getPrice()<=price)
                s += recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }
    public static String writeByComplexity(int complexity){
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (recipe.getComplexity()==Complexity.values()[complexity])
                s += recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }
    public static String writeByComplexityandPrice(double price,int complexity){
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (recipe.getComplexity()==Complexity.values()[complexity] && recipe.getPrice()<=price )
                s += recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }

    public static String writePossibleScaled(double precentage) {
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (Fridge.recipePossibleScaled(recipe, precentage))
                s += recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }

        return s;
    }
}

