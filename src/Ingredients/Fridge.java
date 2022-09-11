package Ingredients;

import java.util.ArrayList;

public abstract class Fridge {
    private static ArrayList<WeightedIngredient> fridgeIngreients = new ArrayList<>();

    public static void addToFridge(WeightedIngredient ingredient, double weight){
        if(!fridgeIngreients.contains(ingredient)){
            fridgeIngreients.add(ingredient);
        }
        ingredient.addWeight(weight);
    }
    public static void removeFromFridge(WeightedIngredient ingredient){
        fridgeIngreients.remove(ingredient);
        ingredient.setWeight(0);
    }
    public static boolean recipePossible(Recipe recipe){
        for(WeightedIngredient ingredient : recipe.getIngredients().keySet()){
            if(recipe.getIngredients().get(ingredient) > ingredient.getWeight()) {
                return false;
            }
        }
        return true;
    }
    public static boolean recipePossibleScaled(Recipe recipe, double percentage){
        for(WeightedIngredient ingredient : recipe.getIngredients().keySet()){
            if((recipe.getIngredients().get(ingredient) *(percentage/100)) > ingredient.getWeight()) {
                System.out.println("nedovoljno");
                return false;
            }
        }
        return true;
    }

    public static void make(Recipe recipe){
        if(Fridge.recipePossible(recipe)){
            for(WeightedIngredient ingredient : recipe.getIngredients().keySet()){
                ingredient.addWeight(-recipe.getIngredients().get(ingredient));

        }
    }
}
    public static String lookInFridge(){
        String s = "";
        for(WeightedIngredient ingredient: fridgeIngreients){
            s+= ingredient.getName() + " " + ingredient.getWeight()+"\n";
        }
        return "Sadrzaj frizidera: \n" + s;
    }
}
