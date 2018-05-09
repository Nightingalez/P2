package com.b139.foodmate;

import java.util.ArrayList;

public class Recipe extends FoodItemList {

    private String name; //Name of the recipe

    //Creates an empty recipe
    public Recipe() {
        super();
    }

    //Creates a recipe with food items
    public Recipe(ArrayList<FoodItem> contents) {

        super(contents);
    }

    //Creates a recipe with food items TODO: Add to class diagram
    public Recipe(FoodItem[] contents) {

        super(contents);
    }

    //Creates a recipe with a name and food items
    public Recipe(String name, ArrayList<FoodItem> contents) {

        this(contents);
        this.name = name;
    }

    //Creates a recipe with a name and food items TODO: Add to class diagram
    public Recipe(String name, FoodItem[] contents) {

        this(contents);
        this.name = name;
    }

    //Returns the name of the recipe
    public String getName() {
        return name;
    }

    //Sets the name of the recipe
    public void setName(String name) {
        this.name = name;
    }
}
