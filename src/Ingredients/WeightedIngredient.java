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
        DataBase.addIngredient(this);

    }
    public static WeightedIngredient createIngredient() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Unesite ime novog sastojka (ukoliko sastojak postoji cena ce mu biti promenjena): ");

        String name = sc.nextLine().trim().toLowerCase(Locale.ROOT);

        System.out.print("Unesite cenu po JM sastojka: ");
        double pricePerUnit = sc.nextDouble();

        if (!DataBase.hasIngredient(name)){
            return  new WeightedIngredient(name,pricePerUnit);

        }
        DataBase.getIngredient(name).setPricePerUnit(pricePerUnit);
        return DataBase.getIngredient(name);
    }


    public void addWeight(double weight){
        this.weight+=weight;
        if(this.weight<0){
            this.weight=0;
        }
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice(){
        return pricePerUnit;
    }

    void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "WeightedIngredient{" +
                "weight=" + weight +
                ", pricePerUnit=" + pricePerUnit +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
