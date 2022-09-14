package Ingredients;

public abstract class Ingredient implements Priceable {
    static int idCount = 1;
    int id;
    String name;

    public String getName() {
        return this.name;
    }


}
