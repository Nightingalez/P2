package com.b139.foodmate;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.ArrayList;


public class DataManager {
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

    //Initializer for figuring out what and when to load at startup
    public void dataInitializer(Context ctx) {
        if (checkFileExists(CATEGORIES, ctx))
        {
            //TODO: load and restore category data
        }
        else
        {
            //TODO: load default category data
        }

        if(checkFileExists(SHOPPING, ctx))
        {
            //TODO: load and restore shopping list data
        }

        if (checkFileExists(STORAGE, ctx))
        {
            //TODO: load and restore storage list data
        }

        if (checkFileExists(RECIPES,ctx))
        {
            //TODO: load and restore recipes
        } else
        {
            //TODO: load default recipes
        }
    }

    //Checks if a file exists
    public boolean checkFileExists(String filename, Context ctx) {
        File file = ctx.getFileStreamPath(filename);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
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
    public void defaultDataGenerator(Context ctx) {
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

        Category misc = new Category("Misc", QuantityUnit.PIECES);
        mainCategories.add(misc);

        Category spices = new Category("Spices", QuantityUnit.GRAMS);
        mainCategories.add(spices);

        Category condiments = new Category("Condiments", QuantityUnit.PIECES);
        mainCategories.add(condiments);

        //Default subCategories
        Category bread = new Category("Bread", QuantityUnit.PIECES, bakery);
        Category ryebread = new Category("Rye Bread", QuantityUnit.PIECES, bakery);
        Category coissant = new Category("Croissant", QuantityUnit.PIECES, bakery);
        Category pie = new Category("Pie", QuantityUnit.PIECES, bakery);
        Category pastry = new Category("Pastry", QuantityUnit.PIECES, bakery);
        Category muffin = new Category("Muffin", QuantityUnit.PIECES, bakery);
        Category cookie = new Category("Cookie", QuantityUnit.PIECES, bakery);
        Category baguette = new Category("Baguette", QuantityUnit.PIECES, bakery);
        Category cake = new Category("Cake", QuantityUnit.PIECES, bakery);
        Category buns = new Category("Buns", QuantityUnit.PIECES, bakery);
        Category tortilla = new Category("Tortilla", QuantityUnit.PIECES, bakery);


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
        Category flour = new Category("Flour", QuantityUnit.GRAMS, grains);
        Category rice = new Category("Rice", QuantityUnit.GRAMS, grains);
        Category oat = new Category("Oat", QuantityUnit.GRAMS, grains);
        Category cereal = new Category("Cereal", QuantityUnit.GRAMS, grains);
        Category pasta = new Category("Pasta", QuantityUnit.GRAMS, grains);
        Category crackers = new Category("Crackers", QuantityUnit.GRAMS, grains);

        Category pork = new Category("Pork", QuantityUnit.GRAMS, meat);
        Category anchovies = new Category("Anchovies", QuantityUnit.GRAMS, meat);
        Category beef = new Category("Beef", QuantityUnit.GRAMS, meat);
        Category sausage = new Category("Sausage", QuantityUnit.PIECES, meat);
        Category steak = new Category("Steak", QuantityUnit.GRAMS, meat);
        Category chicken = new Category("Chicken", QuantityUnit.GRAMS, meat);
        Category bacon = new Category("Bacon", QuantityUnit.GRAMS, meat);
        Category ribs = new Category("Ribs", QuantityUnit.GRAMS, meat);
        Category salami = new Category("Salami", QuantityUnit.GRAMS, meat);
        Category ham = new Category("Ham", QuantityUnit.GRAMS, meat);

        Category garlic = new Category("Garlic", QuantityUnit.PIECES, vegetable);
        Category lettuce = new Category("Lettuce", QuantityUnit.GRAMS, vegetable);
        Category olive = new Category("Olives", QuantityUnit.PIECES, vegetable);
        Category tomato = new Category("Tomato", QuantityUnit.PIECES, vegetable);
        Category cucumber = new Category("Cucumber", QuantityUnit.PIECES, vegetable);
        Category onion = new Category("Onion", QuantityUnit.PIECES, vegetable);
        Category potato = new Category("Potato", QuantityUnit.GRAMS, vegetable);
        Category broccoli = new Category("Broccoli", QuantityUnit.PIECES, vegetable);
        Category celery = new Category("Celery", QuantityUnit.PIECES, vegetable);
        Category beans = new Category("Beans", QuantityUnit.GRAMS, vegetable);

        Category parmesan = new Category("Parmesan", QuantityUnit.GRAMS, dairy);
        Category milk = new Category("Milk", QuantityUnit.MILLILITRES, dairy);
        Category butter = new Category("Butter", QuantityUnit.GRAMS, dairy);
        Category sourcream = new Category("Sour Cream", QuantityUnit.GRAMS, dairy);
        Category cheese = new Category("Cheese", QuantityUnit.GRAMS, dairy);
        Category yogurt = new Category("Yogurt", QuantityUnit.PIECES, dairy);
        Category icecream = new Category("Ice Cream", QuantityUnit.MILLILITRES, dairy);
        Category cottagecheese = new Category("Cottage Cheese", QuantityUnit.MILLILITRES, dairy);

        Category lemon = new Category("Lemon", QuantityUnit.PIECES, fruit);
        Category apple = new Category("Apple", QuantityUnit.PIECES, fruit);
        Category banana = new Category("Banana", QuantityUnit.PIECES, fruit);
        Category avocado = new Category("Avocado", QuantityUnit.PIECES, fruit);
        Category blueberry = new Category("Blueberry", QuantityUnit.GRAMS, fruit);
        Category strawberry = new Category("Strawberry", QuantityUnit.GRAMS, fruit);
        Category peach = new Category("Peach", QuantityUnit.PIECES, fruit);
        Category grape = new Category("Grapes", QuantityUnit.GRAMS, fruit);
        Category orange = new Category("Orange", QuantityUnit.PIECES, fruit);
        Category pineapple = new Category("Pineapple", QuantityUnit.PIECES, fruit);

        Category oliveoil = new Category("Olive Oil", QuantityUnit.PIECES, misc);
        Category vegetableoil = new Category("Vegetable Oil", QuantityUnit.PIECES, misc);
        Category eggs = new Category("Eggs", QuantityUnit.PIECES, misc);
        Category vinegar = new Category("Vinegar", QuantityUnit.PIECES, misc);
        Category coconutoil = new Category("Coconut Oil", QuantityUnit.PIECES, misc);
        Category peanutbutter = new Category("Peanut Butter", QuantityUnit.PIECES, misc);
        Category nutella = new Category("Nutella", QuantityUnit.PIECES, misc);
        Category jam = new Category("Jam", QuantityUnit.PIECES, misc);
        Category honey = new Category("Honey", QuantityUnit.PIECES, misc);



        Category salt = new Category("Salt", QuantityUnit.GRAMS, spices);
        Category pepper = new Category("Pepper", QuantityUnit.GRAMS, spices);
        Category paprika = new Category("Paprika", QuantityUnit.GRAMS, spices);
        Category mint = new Category("Mint", QuantityUnit.GRAMS, spices);
        Category basil = new Category("Basil", QuantityUnit.GRAMS, spices);
        Category parsley = new Category("Parsley", QuantityUnit.GRAMS, spices);
        Category thyme = new Category("Thyme", QuantityUnit.GRAMS, spices);
        Category rosemary = new Category("Rosemary", QuantityUnit.GRAMS, spices);
        Category oregano = new Category("Oregano", QuantityUnit.GRAMS, spices);
        Category cumin = new Category("Cumin", QuantityUnit.GRAMS, spices);

        Category ketchup = new Category("Ketchup", QuantityUnit.PIECES, condiments);
        Category mayo = new Category("Mayo", QuantityUnit.PIECES, condiments);
        Category mustard = new Category("Mustard", QuantityUnit.PIECES, condiments);
        Category remoulade = new Category("Remoulade", QuantityUnit.PIECES, condiments);
        Category bbq = new Category("Barbecue Sauce", QuantityUnit.PIECES, condiments);
        Category soysauce = new Category("Soy Sauce", QuantityUnit.PIECES, condiments);
        Category fishsauce = new Category("Fish Sauce", QuantityUnit.PIECES, condiments);
        


        //Default recipes
        recipes.add(new Recipe("Classic Burger", new FoodItem[]{
                new FoodItem(beef), new FoodItem(eggs),
                new FoodItem(onion), new FoodItem(bread),
                new FoodItem(garlic), new FoodItem(salt),
                new FoodItem(pepper), new FoodItem(buns),
                new FoodItem(lettuce), new FoodItem(tomato),
                new FoodItem(ketchup), new FoodItem(mayonaise)}));

        recipes.add(new Recipe("Caesar Salad", new FoodItem[]{
                new FoodItem(olive), new FoodItem(baguette),
                new FoodItem(oliveoil), new FoodItem(garlic),
                new FoodItem(lemon), new FoodItem(parmesan),
                new FoodItem(anchovies), new FoodItem(eggs),
                new FoodItem(pepper), new FoodItem(salt),
                new FoodItem(lettuce)}));
    }
}
