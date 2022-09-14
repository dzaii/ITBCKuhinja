package Ingredients;

import java.util.Locale;
import java.util.Scanner;

public class WeightedIngredient extends Ingredient{
    private double weight;
    private double pricePerUnit;

    WeightedIngredient(String name,double pricePerUnit){
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.id = idCount++;
    }
    void addWeight(double weight){
        this.weight+=weight;
        if(this.weight<0){
            this.weight=0;
        }
    }

    public double getPrice(){
        return pricePerUnit;
    }

    void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

     double getWeight() {
        return weight;
    }
    int getId() {
        return this.id;
    }
}
