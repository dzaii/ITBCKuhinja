package Ingredients;

public abstract class Ingredient implements Priceable {
    static int idCount=0;
    int id;
     String name;
    public String getName(){
        return this.name;
    }


}
