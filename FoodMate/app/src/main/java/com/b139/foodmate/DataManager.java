package com.b139.foodmate;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataManager {
    //TODO: create default file names
    private static ArrayList<Category> mainCategories;
    final static String CATEGORIES = "categories.txt";
    final static String DEFAULT_CATEGORIES = "default_categories.txt";

    private static FoodItemList shoppingList;
    final static String SHOPPING = "shopping.txt";

    private static FoodItemList storage;
    final static String STORAGE = "storage.txt";

    private static ArrayList<Recipe> recipes;
    final static String RECIPES = "recipes.txt";
    final static String DEFAULT_RECIPES = "default_recipes.txt";

    public void saveData(String filename, String[] stringArray, Context ctx) {
        OutputStreamWriter outputStreamWriter;

        try {
            outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput(filename, Context.MODE_PRIVATE));
            for (String s : stringArray) {
                outputStreamWriter.write(s + "\n");
            }
            outputStreamWriter.close();

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public String[] loadData(String filename, Context ctx) {
        ArrayList<String> loaded = new ArrayList<>();

        try {
            InputStream inputStream = ctx.openFileInput(filename);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";


                while ((receiveString = bufferedReader.readLine()) != null) {
                    loaded.add(receiveString);
                }

                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        String[] result = new String[loaded.size()];
        loaded.toArray(result);
        return result;
    }

    //Generates dummy data for testing purposes
    public void generateDummyData() {
        //Create main categories
        Category meat = new Category("Meat", QuantityUnit.GRAMS);
        mainCategories.add(meat);
        Category vegetable = new Category("Vegetable", QuantityUnit.PIECES);
        mainCategories.add(vegetable);
        Category dairy = new Category("Dairy", QuantityUnit.GRAMS);
        mainCategories.add(dairy);
        Category bakery = new Category("Bakery", QuantityUnit.GRAMS);
        mainCategories.add(bakery);

        //Create subcategories
        Category pork = new Category("Pork", QuantityUnit.GRAMS, meat);
        Category porkchop = new Category("Porkchop", QuantityUnit.PIECES, pork);
        Category bacon = new Category("Bacon", QuantityUnit.GRAMS, pork);
        Category beef = new Category("Beef", QuantityUnit.GRAMS, meat);
        Category minced = new Category("Minced beef", QuantityUnit.GRAMS, beef);
        Category salad = new Category("Salad", QuantityUnit.PIECES, vegetable);
        Category rucola = new Category("Rucola", QuantityUnit.PIECES, salad);
        Category iceberg = new Category("Iceberg", QuantityUnit.PIECES, salad);
        Category cucumber = new Category("Cucumber", QuantityUnit.PIECES, vegetable);
        Category tomato = new Category("Tomato", QuantityUnit.PIECES, vegetable);
        Category milk = new Category("Milk", QuantityUnit.MILLILITRES, dairy);
        Category butter = new Category("Butter", QuantityUnit.GRAMS, dairy);
        Category egg = new Category("Egg", QuantityUnit.PIECES, dairy);
        Category bread = new Category("Bread", QuantityUnit.PIECES, bakery);
        Category ryebread = new Category("Rye Bread", QuantityUnit.PIECES, bread);
        Category toast = new Category("Toast", QuantityUnit.PIECES, bread);

        //Create shopping list
        shoppingList.addFoodItem(new FoodItem(minced));
        shoppingList.addFoodItem(new FoodItem(5, tomato));
        shoppingList.addFoodItem(new FoodItem(1000, milk));

        //Create storage list
        shoppingList.addFoodItem(new FoodItem(iceberg));
        shoppingList.addFoodItem(new FoodItem(cucumber));
        shoppingList.addFoodItem(new FoodItem(6, egg));
        shoppingList.addFoodItem(new FoodItem(butter));
        shoppingList.addFoodItem(new FoodItem(toast));

        //Create 3 recipes
        recipes.add(new Recipe("Eggy bread", new FoodItem[]{new FoodItem(egg), new FoodItem(toast)}));
        recipes.add(new Recipe("Hamburger with bacon shroud", new FoodItem[]{new FoodItem(minced), new FoodItem(bacon)}));
        recipes.add(new Recipe("Basic salad", new FoodItem[]{new FoodItem(iceberg), new FoodItem(tomato), new FoodItem(cucumber)}));
    }

    //TODO: create a initializer for figuring out what and when to load
    public void dataInitializer(Context ctx)
    {

    }

    //TODO: create method for checking if a file exists
    public boolean checkFileExists(String name, Context ctx)
    {

    }

    //TODO: create a shutdown saver

    //TODO: create category loader

    //TODO: create category stringarray generator

    //TODO: create food item loader

    //TODO: create food item stringarray generator

    //TODO: create food item list loader

    //TODO: create storage & shopping list loader

    //TODO: create food item list stringarray generator

    //TODO: create storage & shopping list saver

    //TODO: create recipe loader

    //TODO: create recipe stringarray generator

    //TODO: create reset

    //TODO: create a method that generates/restores default save files
    public void defaultDataGenerator(Context ctx)
    {
        //Default main categories
        Category meat = new Category("Meat", QuantityUnit.GRAMS);
        mainCategories.add(meat);
        Category vegetable = new Category("Vegetable", QuantityUnit.PIECES);
        mainCategories.add(vegetable);
        Category dairy = new Category("Dairy", QuantityUnit.GRAMS);
        mainCategories.add(dairy);
        Category fruit = new Category("Fruit", QuantityUnit.PIECES);
        mainCategories.add(fruit);
        Category bakery = new Category("Bakery", QuantityUnit.PIECES);
        mainCategories.add(bakery);

        Category snacks = new Category("Snacks", QuantityUnit.PIECES);
        mainCategories.add(snacks);

        Category beverages = new Category("Beverages", QuantityUnit.PIECES);
        mainCategories.add(beverages);

        Category grains = new Category("Grains", QuantityUnit.GRAMS);
        mainCategories.add(grains);

        //Default subCategories
        Category bread = new Category("Bread", QuantityUnit.PIECES, bakery);
        Category ryebread = new Category("Rye Bread", QuantityUnit.PIECES, bakery);
        Category coissant = new Category("Croissant", QuantityUnit.PIECES, bakery);
        Category pie = new Category("Pie", QuantityUnit.PIECES, bakery);
        Category pastry = new Category("Pastry", QuantityUnit.PIECES, bakery);
        Category muffin = new Category("Muffin", QuantityUnit.PIECES, bakery);
        Category cookie = new Category("Cookie", QuantityUnit.PIECES, bakery);
        Category baguette = new Category("Baguette", QuantityUnit.PIECES, bakery);

        Category chips = new Category("Chips", QuantityUnit.PIECES, snacks);
        Category nuts = new Category("Nuts", QuantityUnit.PIECES, snacks);
        Category popcorn = new Category("Popcorn", QuantityUnit.PIECES, snacks);
        Category candy = new Category("Candy", QuantityUnit.PIECES, snacks);
        Category chocolate = new Category("Chocolate", QuantityUnit.PIECES, snacks);
        Category granolabar = new Category("Granola Bar", QuantityUnit.PIECES, snacks);
        Category trailmix = new Category("Trail Mix", QuantityUnit.PIECES, snacks);

        Category water = new Category("Water", QuantityUnit.PIECES, beverages);
        Category soda = new Category("Soda", QuantityUnit.PIECES, beverages);
        Category beer = new Category("Beer", QuantityUnit.PIECES, beverages);
        Category tea = new Category("Tea", QuantityUnit.PIECES, beverages);
        Category coffee = new Category("Coffee", QuantityUnit.PIECES, beverages);
        Category juice = new Category("Juice", QuantityUnit.PIECES, beverages);
        Category wine = new Category("Wine", QuantityUnit.PIECES, beverages);
        Category spirits = new Category("Spirits", QuantityUnit.PIECES, beverages);

        Category wheat = new Category("Wheat", QuantityUnit.GRAMS, grains);
        Category rice = new Category("Rice", QuantityUnit.GRAMS, grains);
        Category oat = new Category("Oat", QuantityUnit.GRAMS, grains);
        Category cereal = new Category("Cereal", QuantityUnit.GRAMS, grains);
        Category pasta = new Category("Pasta", QuantityUnit.GRAMS, grains);
        Category crackers = new Category("Crackers", QuantityUnit.GRAMS, grains);
        Category tortilla = new Category("Tortilla", QuantityUnit.GRAMS, grains);

        Category pork = new Category("Pork", QuantityUnit.GRAMS, meat);
        Category apple = new Category("Apple", QuantityUnit.PIECES, fruit);
        Category garlic = new Category("Garlic", QuantityUnit.PIECES, vegetable);
        Category parmesan = new Category("Parmesan", QuantityUnit.GRAMS, dairy);
        Category anchovies = new Category("Anchovies", QuantityUnit.GRAMS, meat);
        Category blackPepper = new Category("Black Pepper", QuantityUnit.GRAMS, fruit);
        Category lemon = new Category("Lemon", QuantityUnit.MILLILITRES, fruit);

        //Default recipes

    }
}
