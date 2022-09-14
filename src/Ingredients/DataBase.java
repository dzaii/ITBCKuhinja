package Ingredients;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

public abstract class DataBase {
    private static HashMap<Integer, WeightedIngredient> ingHM = new HashMap<>();
    private static HashMap<Integer, Recipe> recipeHM = new HashMap<>();



    static void addRecipe(Recipe recipe) {
        recipeHM.put(recipe.getId(), recipe);
    }

    static void putIng(WeightedIngredient ingredient){
        ingHM.put(ingredient.getId(),ingredient);
    }

    public static void initialiseDB() {
        DataBase.putIng( new WeightedIngredient("butter", 1000));
        DataBase.putIng( new WeightedIngredient("egg", 16));
        DataBase.putIng( new WeightedIngredient("flour", 60));
        DataBase.putIng( new WeightedIngredient("olive oil", 800));
        DataBase.putIng( new WeightedIngredient("milk", 130));
        DataBase.putIng( new WeightedIngredient("onion", 60));
        DataBase.putIng( new WeightedIngredient("rice", 80));
        DataBase.putIng( new WeightedIngredient("potato", 50));
        DataBase.putIng( new WeightedIngredient("mustard", 350));
        DataBase.putIng( new WeightedIngredient("chicken", 450));
        DataBase.putIng( new WeightedIngredient("tomato", 160));
        DataBase.putIng( new WeightedIngredient("bell pepper", 180));
        DataBase.putIng( new WeightedIngredient("mushroom", 200));
        DataBase.putIng( new WeightedIngredient("mozzarella", 1300));
        DataBase.putIng( new WeightedIngredient("beef", 1100));

        Recipe omelet = new Recipe("Tomato and Green Pepper Omelet".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(omelet);
        omelet.addIngredient("butter", 0.05);
        omelet.addIngredient("onion", 0.07);
        omelet.addIngredient("olive oil", 0.03);
        omelet.addIngredient("egg", 3);
        omelet.addIngredient("bell pepper", 0.1);
        omelet.addIngredient("tomato", 0.1);

        Recipe hamhash = new Recipe("Hamburger Hash".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(hamhash);
        hamhash.addIngredient("potato", 0.3);
        hamhash.addIngredient("onion", 0.1);
        hamhash.addIngredient("olive oil", 0.03);
        hamhash.addIngredient("egg", 8);
        hamhash.addIngredient("butter", 0.1);
        hamhash.addIngredient("beef", 0.35);

        Recipe potfar = new Recipe("Potato Farls".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(potfar);
        potfar.addIngredient("butter", 0.05);
        potfar.addIngredient("potato", 0.5);
        potfar.addIngredient("olive oil", 0.03);
        potfar.addIngredient("egg", 2);
        potfar.addIngredient("flour", 0.1);
        potfar.addIngredient("tomato", 0.25);

        Recipe chimozz = new Recipe("Chicken Breast With Mozzarella Cheese".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(chimozz);
        chimozz.addIngredient("butter", 0.07);
        chimozz.addIngredient("mozzarella", 0.2);
        chimozz.addIngredient("olive oil", 0.03);
        chimozz.addIngredient("flour", 0.1);
        chimozz.addIngredient("chicken", 0.25);


        Recipe york = new Recipe("Onion Yorkshire Puddings Recipe".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(york);
        york.addIngredient("butter", 0.07);
        york.addIngredient("flour", 0.2);
        york.addIngredient("olive oil", 0.03);
        york.addIngredient("egg", 2);
        york.addIngredient("onion", 0.25);

        Recipe frittata = new Recipe("Veggie Frittata".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(frittata);
        frittata.addIngredient("butter", 0.07);
        frittata.addIngredient("egg", 12);
        frittata.addIngredient("olive oil", 0.03);
        frittata.addIngredient("bell pepper", 0.25);
        frittata.addIngredient("mushroom", 0.2);

        Recipe gravy = new Recipe("Creamy Mushroom Gravy".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(gravy);
        gravy.addIngredient("butter", 0.07);
        gravy.addIngredient("flour", 0.05);
        gravy.addIngredient("olive oil", 0.03);
        gravy.addIngredient("milk", 0.2);
        gravy.addIngredient("mushroom", 0.4);

        Recipe knoepfle = new Recipe("Knoepfle".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(knoepfle);
        knoepfle.addIngredient("butter", 0.2);
        knoepfle.addIngredient("flour", 0.2);
        knoepfle.addIngredient("onion", 0.2);
        knoepfle.addIngredient("milk", 0.4);
        knoepfle.addIngredient("egg", 4);

        Recipe mush = new Recipe("Mushroom and Roasted Red Pepper Tortilla".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(mush);
        mush.addIngredient("olive oil", 0.03);
        mush.addIngredient("potato", 0.5);
        mush.addIngredient("onion", 0.1);
        mush.addIngredient("mushroom", 0.2);
        mush.addIngredient("bell pepper", 0.1);
        mush.addIngredient("egg", 8);

        Recipe pelmeni = new Recipe("Pelmeni".trim().toLowerCase(Locale.ROOT), Complexity.values()[(int)(Math.random()*Complexity.values().length)]);
        DataBase.addRecipe(pelmeni);
        pelmeni.addIngredient("olive oil", 0.03);
        pelmeni.addIngredient("potato", 0.5);
        pelmeni.addIngredient("onion", 0.1);
        pelmeni.addIngredient("tomato", 0.1);
        pelmeni.addIngredient("flour", 0.2);
        pelmeni.addIngredient("egg", 1);
        pelmeni.addIngredient("beef", 0.12);



        for (WeightedIngredient ingredient : DataBase.ingHM.values()) {
            Fridge.addToFridge(ingredient,((int) (Math.random() * 100)) *0.1);
        }

    }

    public static HashMap<Integer, WeightedIngredient> getIngHM() {
        return ingHM;
    }

    public static HashMap<Integer, Recipe> getRecipeHM() {
        return recipeHM;
    }

    public static boolean hasRecipe(int id) {
        return recipeHM.containsKey(id);
    }

    public static Recipe getRecipe(int id) {
        return recipeHM.get(id);
    }


    public static String writeIng() {
        String s = "";
        for (WeightedIngredient ingredient : DataBase.ingHM.values()) {
            s += ingredient.getId() + " : " + ingredient.getName() + "\n";
        }
        return s;
    }

    public static String writeRecipe() {
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            s += recipe.getId() + " : " + recipe.getName() + "....." + recipe.getPrice() + " din.\n" + "       "+ recipe.toString()+"\n\n";
        }
        return s;
    }

    public static String writePossible() {
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (Fridge.recipePossible(recipe))
                s += recipe.getId() + " : " +recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }
    public static String writePossibleForPrice(double price){
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (recipe.getPrice()<=price)
                s += recipe.getId() + " : " +recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }
    public static String writeByComplexity(int complexity){
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (recipe.getComplexity()==Complexity.values()[complexity])
                s += recipe.getId() + " : " +recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }
    public static String writeByComplexityandPrice(double price,int complexity){
        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (recipe.getComplexity()==Complexity.values()[complexity] && recipe.getPrice()<=price )
                s += recipe.getId() + " : " +recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }

    public static String writePossibleScaled(double precentage) {

        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (Fridge.recipePossibleScaled(recipe, precentage))
                s += recipe.getId() + " : " + recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }

        return s;
    }
    public static String writeFav() {

        String s = "";
        for (Recipe recipe : DataBase.recipeHM.values()) {
            if (recipe.isFav())
                s += recipe.getId() + " : " + recipe.getName() + "....." + recipe.getPrice() + " din.\n";
        }
        return s;
    }
}

