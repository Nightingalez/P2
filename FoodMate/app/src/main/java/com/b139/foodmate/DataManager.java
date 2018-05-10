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
    private static ArrayList<Category> mainCategories = new ArrayList<Category>();
    final static String CATEGORIES = "categories.txt";

    private static FoodItemList shoppingList = new FoodItemList();
    final static String SHOPPING = "shopping.txt";

    private static FoodItemList storage = new FoodItemList();
    final static String STORAGE = "storage.txt";

    private static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    final static String RECIPES = "recipes.txt";

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
    public static void generateDummyData() {
        //Create main categories
        Category meat = new Category("Meat", QuantityUnit.GRAMS);
        DataManager.mainCategories.add(meat);
        Category vegetable = new Category("Vegetable", QuantityUnit.PIECES);
        DataManager.mainCategories.add(vegetable);
        Category dairy = new Category("Dairy", QuantityUnit.GRAMS);
        DataManager.mainCategories.add(dairy);
        Category bakery = new Category("Bakery", QuantityUnit.GRAMS);
        DataManager.mainCategories.add(bakery);

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
        DataManager.shoppingList.addFoodItem(new FoodItem(minced));
        DataManager.shoppingList.addFoodItem(new FoodItem(5, tomato));
        DataManager.shoppingList.addFoodItem(new FoodItem(1000, milk));

        //Create storage list
        DataManager.storage.addFoodItem(new FoodItem(iceberg));
        DataManager.storage.addFoodItem(new FoodItem(cucumber));
        DataManager.storage.addFoodItem(new FoodItem(6, egg));
        DataManager.storage.addFoodItem(new FoodItem(butter));
        DataManager.storage.addFoodItem(new FoodItem(toast));

        //Create 3 recipes
        DataManager.recipes.add(new Recipe("Eggy bread", new FoodItem[]{new FoodItem(egg), new FoodItem(toast)}));
        DataManager.recipes.add(new Recipe("Hamburger with bacon shroud", new FoodItem[]{new FoodItem(minced), new FoodItem(bacon)}));
        DataManager.recipes.add(new Recipe("Basic salad", new FoodItem[]{new FoodItem(iceberg), new FoodItem(tomato), new FoodItem(cucumber)}));
    }

    //TODO: create a initializer for figuring out what and when to load

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
}
