package Ingredients;

import java.util.HashMap;

public class Recipe implements Priceable {
    private String name;
    private Complexity complexity;
    private HashMap<WeightedIngredient,Double> ingredients;

    Recipe(String name,Complexity complexity){
        this.name = name.trim().toUpperCase();
        this.complexity = complexity;
        this.ingredients =  new HashMap<>();
    }

    boolean addIngredient(String name,double weight){
        if(DataBase.hasIngredient(name)){
            this.ingredients.put(DataBase.getIngredient(name),weight);
            return true;
        }
        return false;
    }
     void removeIngredient(String name){
        this.ingredients.remove(DataBase.getIngredient(name));
    }

    public String toString(){
        String s = "";
        for(Ingredient ingredient : this.ingredients.keySet()){
            s+= ingredient.getName() + "..." + this.ingredients.get(ingredient)+", ";
        }
        return s;
    }

    public String getName() {
        return name;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public double getPrice() {
        double price = 0;
        for (Ingredient ingredient : this.ingredients.keySet()) {
            price += ingredient.getPrice() * this.ingredients.get(ingredient);
        }
        return price;
    }
    Recipe getScaledRecipe(double percentage){
        Recipe scaledRecipe = new Recipe(this.name + percentage + "%",this.complexity);
        for(Ingredient ingredient: this.ingredients.keySet()){
            scaledRecipe.addIngredient(ingredient.getName(),this.ingredients.get(ingredient)*(percentage/100));
        }
        return scaledRecipe;
    }

    public HashMap<WeightedIngredient, Double> getIngredients() {
        return ingredients;
    }
}
