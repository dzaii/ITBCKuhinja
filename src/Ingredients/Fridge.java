package Ingredients;

import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Fridge {
    private static ArrayList<WeightedIngredient> fridgeIngreients = new ArrayList<>();

    public static void addToFridge(WeightedIngredient ingredient, double weight) {
        if (!fridgeIngreients.contains(ingredient)) {
            fridgeIngreients.add(ingredient);
        }
        ingredient.addWeight(weight);
    }

    public static void removeFromFridge(WeightedIngredient ingredient) {
        fridgeIngreients.remove(ingredient);
        ingredient.addWeight(-ingredient.getWeight());
    }

    public static boolean recipePossible(Recipe recipe) {
        for (WeightedIngredient ingredient : recipe.getIngredients().keySet()) {
            if (recipe.getIngredients().get(ingredient) > ingredient.getWeight()) {
                return false;
            }
        }
        return true;
    }

    public static boolean recipePossibleScaled(Recipe recipe, double percentage) {
        for (WeightedIngredient ingredient : recipe.getIngredients().keySet()) {
            if ((recipe.getIngredients().get(ingredient) * (percentage / 100)) > ingredient.getWeight()) {
                return false;
            }
        }
        return true;
    }

    public static void make(Recipe recipe) {
        if (Fridge.recipePossible(recipe)) {
            for (WeightedIngredient ingredient : recipe.getIngredients().keySet()) {
                ingredient.addWeight(-recipe.getIngredients().get(ingredient));

            }
        }
    }

    public static String lookInFridge() {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        String s = "";
        for (WeightedIngredient ingredient : fridgeIngreients) {
            s += ingredient.getId() + " : " + ingredient.getName() + " " + formatter.format(ingredient.getWeight()) + "\n";
        }
        return "Sadrzaj frizidera: \n" + s;
    }
}
