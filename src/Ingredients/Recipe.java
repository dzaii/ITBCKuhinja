package Ingredients;

import java.util.HashMap;

public class Recipe implements Priceable {
    static int idCount = 1;
    int id;

    private String name;
    private Complexity complexity;

    private boolean isFav = false;
    private HashMap<WeightedIngredient, Double> ingredients;

    Recipe(String name, Complexity complexity) {
        this.name = name.trim().toUpperCase();
        this.complexity = complexity;
        this.ingredients = new HashMap<>();
        this.id = idCount++;
    }

    public int getId() {
        return id;
    }

    boolean addIngredient(String name, double weight) {
        for (WeightedIngredient ingredient : DataBase.getIngHM().values()) {
            if (ingredient.getName().equals(name)) {
                this.ingredients.put(ingredient, weight);
                return true;
            }
        }
        return false;
    }

    void removeIngredient(int id) {
        this.ingredients.remove(DataBase.getIngHM().get(id));
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

    Recipe getScaledRecipe(double percentage) {
        Recipe scaledRecipe = new Recipe(this.name + percentage + "%", this.complexity);
        for (Ingredient ingredient : this.ingredients.keySet()) {
            scaledRecipe.addIngredient(ingredient.getName(), this.ingredients.get(ingredient) * (percentage / 100));
        }
        return scaledRecipe;
    }

    @Override
    public String toString() {
        String s = "| ";
        for (WeightedIngredient ingredient : this.ingredients.keySet()) {
            s += ingredient.getName() + "..." + ingredients.get(ingredient) + " | ";
        }
        return s;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    HashMap<WeightedIngredient, Double> getIngredients() {
        return ingredients;
    }
}
