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
import java.util.Collections;

import static java.util.Arrays.copyOfRange;


public class DataManager {
    private static ArrayList<Category> mainCategories = new ArrayList<Category>();
    final static String CATEGORIES = "categories.txt";
    final static String DEFAULT_CATEGORIES = "default_categories.txt";

    private static FoodItemList shoppingList = new FoodItemList();
    final static String SHOPPING = "shopping.txt";

    private static FoodItemList storage = new FoodItemList();
    final static String STORAGE = "storage.txt";

    private static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    final static String RECIPES = "recipes.txt";
    final static String DEFAULT_RECIPES = "default_recipes.txt";

    public static void saveData(String filename, String[] stringData, Context ctx) {
        OutputStreamWriter outputStreamWriter;

        try {
            outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput(filename, Context.MODE_PRIVATE));
            for (String s : stringData) {
                outputStreamWriter.write(s + "\n");
            }
            outputStreamWriter.close();

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String[] loadData(String filename, Context ctx) {
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

    //Initializer for figuring out what and when to load at startup
    public void dataInitializer(Context ctx) {
        if (checkFileExists(CATEGORIES, ctx)) {
            //load and restore category data
            categoriesFromStrings(loadData(CATEGORIES, ctx));
        } else {
            //load default category data
            categoriesFromStrings(loadData(DEFAULT_CATEGORIES, ctx));
        }

        if (checkFileExists(SHOPPING, ctx)) {
            shoppingList = foodItemListFromStrings(loadData(SHOPPING, ctx));
        }

        if (checkFileExists(STORAGE, ctx)) {
            storage = foodItemListFromStrings(loadData(STORAGE, ctx));
        }

        if (checkFileExists(RECIPES, ctx)) {
            //load and restore recipes
            recipeFromStrings(loadData(RECIPES, ctx));
        } else {
            //load default recipes
            recipeFromStrings(loadData(DEFAULT_RECIPES, ctx));
        }
    }

    //Checks if a file exists
    public static boolean checkFileExists(String filename, Context ctx) {
        File file = ctx.getFileStreamPath(filename);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    //saves all data
    public static void saveAll(Context ctx)
    {
        saveCategories(ctx);
        saveShopping(ctx);
        saveStorage(ctx);
        saveRecipes(ctx);
    }

    //saves category data
    public static void saveCategories(Context ctx) {
        saveData(CATEGORIES, allCategoriesToStrings(), ctx);
    }

    //saves shopping list data
    public static void saveShopping(Context ctx) {
        saveData(SHOPPING, foodItemListToStrings(shoppingList), ctx);
    }

    //saves storage data
    public static void saveStorage(Context ctx) {
        saveData(STORAGE, foodItemListToStrings(storage), ctx);
    }

    //saves recipe data
    public static void saveRecipes(Context ctx) {
        saveData(RECIPES, allRecipesToStrings(), ctx);
    }

    //takes a string array of information from a category save file, and rebuilds them
    public static void categoriesFromStrings(String[] stringData) {
        int i = 0;
        ArrayList<Category> tempCat = new ArrayList<Category>();

        while (i < stringData.length) {
            if (stringData[i] == DataTag.CATEGORY) {
                i++; //skip the first line with the CAT tag, go to the ID
                int id = Integer.parseInt(stringData[i]);
                i++; //go to the name
                String name = stringData[i];
                i++; // go to the quantity unit
                QuantityUnit unit = QuantityUnit.values()[Integer.parseInt(stringData[i])];
                i++; //go to the parent id
                int parentID = id = Integer.parseInt(stringData[i]);

                if (parentID != 0) {// if the category has no parent, create the category and add it directly to the main category list
                    Category loaded = new Category(name, unit);
                    loaded.setID(id);
                    mainCategories.add(loaded);
                    tempCat.add(loaded);

                } else { // else, find its parent, and add it under it
                    Category parent = null;
                    for (Category c : tempCat) {
                        if (c.getID() == id) {
                            parent = c;
                        }
                    }

                    if (parent != null) { //if the correct parent could be found, create the category
                        Category loaded = new Category(name, unit, parent);
                        loaded.setID(id);
                        parent.addSubcategory(loaded);
                        tempCat.add(loaded);
                    }
                }
            } else if (stringData[i] == DataTag.END) { //if an additional end tag is hit, stop loading
                break;
            }
        }
    }

    // lists all categories, including subcategories, in a list
    public static ArrayList<Category> listAllCategories(ArrayList<Category> list) {
        ArrayList<Category> result = new ArrayList<Category>();

        for (Category c : list) {
            result.add(c);
            if (c.hasSubcategories()) {
                result.addAll(listAllCategories(c.getSubcategories()));
            }
        }
        return result;
    }

    //creates a category string array, which contains all categories in the system
    public static String[] allCategoriesToStrings() {
        //first create a temporary list of all categories
        ArrayList<Category> tempCat = new ArrayList<Category>();
        for (Category c : mainCategories) {
            tempCat.addAll(c.getCategoryBranch());
        }

        //then create a temporary array list of strings
        ArrayList<String> tempString = new ArrayList<String>();

        //add category strings to list
        for (Category c : tempCat) {
            Collections.addAll(tempString, categoryToStrings(c));
        }

        //add final end tag
        tempString.add(DataTag.END);

        //convert and return string array
        String[] result = new String[tempString.size()];
        tempString.toArray(result);
        return result;

    }

    //creates a string array from a category
    public static String[] categoryToStrings(Category category) {
        String[] result = new String[]{
                DataTag.CATEGORY,
                String.valueOf(category.getID()),
                category.getName(),
                String.valueOf(category.getParentCategory().getID()),
                DataTag.END
        };
        return result;
    }

    // recreate a food item from string data
    public static FoodItem foodItemFromStrings(String[] stringData) {
        int amount = Integer.parseInt(stringData[1]);
        int catId = Integer.parseInt(stringData[2]);

        //find the correct category based on id
        Category foodItemCategory = null;
        for (Category c : mainCategories) {
            boolean found = false;

            for (Category t : c.getCategoryBranch()) {
                if (t.getID() == catId) {
                    foodItemCategory = t;
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        if (foodItemCategory != null) {
            //rebuild and return the food item
            return new FoodItem(amount, foodItemCategory);
        }
        return null;
    }

    // recreate food items from string data
    public static FoodItem[] foodItemsFromStrings(String[] stringData) {
        // create a temporary array list
        ArrayList<FoodItem> tempList = new ArrayList<FoodItem>();

        //go through the string array
        stringScanner:
        for (int i = 0; i < stringData.length; i++) {
            switch (stringData[i]) {
                case DataTag.ITEM: //pass on this and the next 3 lines to the food item builder as a string array
                    String[] foodItemData = copyOfRange(stringData, i, i + 3);
                    FoodItem item = foodItemFromStrings(foodItemData);
                    tempList.add(item);

                    //move the loop 3 lines ahead
                    i += 3;
                    break;

                case DataTag.END: //if an additional end tag is hit, stop the loop
                    break stringScanner;

                default:
                    break;
            }
        }

        //convert and return food item array
        FoodItem[] result = new FoodItem[tempList.size()];
        tempList.toArray(result);
        return result;
    }

    //creates a string array from a food item
    public static String[] foodItemToStrings(FoodItem item) {
        String[] result = new String[]{
                DataTag.ITEM,
                String.valueOf(item.getAmount()),
                String.valueOf(item.getCategory().getID()),
                DataTag.END
        };
        return result;
    }

    //creates a food item list from string data
    public static FoodItemList foodItemListFromStrings(String[] stringData) {
        //first pass the data to foodItemsFromStrings to get an array of food items
        FoodItem[] tempItems = foodItemsFromStrings(stringData);

        //create and return a food item list from the array
        FoodItemList result = new FoodItemList(tempItems);
        return result;
    }


    //creates a string array from a food item list
    public static String[] foodItemListToStrings(FoodItemList list) {
        //first create a temporary array list
        ArrayList<String> tempList = new ArrayList<String>();
        tempList.add(DataTag.LIST);

        //add strings for each food item in the list
        for (FoodItem i : list.getContents()) {
            Collections.addAll(tempList, foodItemToStrings(i));
        }

        //add final end tag
        tempList.add(DataTag.END);

        //convert and return string array
        String[] result = new String[tempList.size()];
        tempList.toArray(result);
        return result;
    }


    //takes a string array of information from a recipe save file, and rebuilds them
    public static void recipesFromStrings(String[] stringData) {
        //go through the data to "snip out" data for a single recipe
        int openTags = 1;
        int recipeStartLine = 0;
        dataScanner:
        for (int i = 0; i < stringData.length; i++) {
            switch (stringData[i]) {
                case DataTag.RECIPE:
                    recipeStartLine = i;
                    openTags++;
                    break;

                case DataTag.LIST:
                case DataTag.ITEM:
                    openTags++;
                    break;

                case DataTag.END:
                    openTags--;

                    if (openTags == 1) {//if the end of a segment of recipe data is reach, make a copy of the read info, and build a recipe
                        String[] recipeData = copyOfRange(stringData, recipeStartLine, i);
                        recipes.add(recipeFromStrings(recipeData));

                    } else if (openTags == 0) {//if the end of the save file is reached, stop the loop
                        break dataScanner;
                    }
                    break;

                default:
                    break;
            }
        }
    }

    //creates a recipe from string data
    public static Recipe recipeFromStrings(String[] stringData) {
        //First, grab the name of the recipe
        String name = stringData[1];

        //pass the remaining data to foodItemsFromStrings to get an array of food items
        FoodItem[] tempItems = foodItemsFromStrings(stringData);

        //create and return a recipe
        Recipe result = new Recipe(name, tempItems);
        return result;
    }

    //creates a string array from a recipe
    public static String[] recipeToStrings(Recipe recipe) {
        //first create a temporary array list
        ArrayList<String> tempRecipe = new ArrayList<String>();

        //add the first pieces of info
        tempRecipe.add(DataTag.RECIPE);
        tempRecipe.add(recipe.getName());

        //add its contents
        for (FoodItem f : recipe.getContents()) {
            Collections.addAll(tempRecipe, foodItemToStrings(f));
        }

        //add end tag
        tempRecipe.add(DataTag.END);

        //convert and return string array
        String[] result = new String[tempRecipe.size()];
        tempRecipe.toArray(result);
        return result;
    }

    //creates a recipe string array, which contains all recipes in the system
    public static String[] allRecipesToStrings() {
        //first create a temporary array list
        ArrayList<String> tempString = new ArrayList<String>();

        //add each recipe string array
        for (Recipe r : recipes) {
            Collections.addAll(tempString, recipeToStrings(r));
        }

        //add final end tag
        tempString.add(DataTag.END);

        //convert and return string array
        String[] result = new String[tempString.size()];
        tempString.toArray(result);
        return result;
    }


    //deletes all current data
    public void clearCurrentData() {
        mainCategories = new ArrayList<Category>();
        shoppingList = new FoodItemList();
        storage = new FoodItemList();
        recipes = new ArrayList<Recipe>();
    }

    //restores the system to default values
    public void restoreDefault(Context ctx) {
        clearCurrentData();
        defaultDataGenerator();

        //for safeties sake, also overwrite the default data files
        saveData(DEFAULT_CATEGORIES, allCategoriesToStrings(), ctx);
        saveData(DEFAULT_RECIPES, allRecipesToStrings(), ctx);
    }

    //inserts default data into the system. NOTE: Does not clear or delete anything! Make sure to do that before calling this to avoid duplicate data
    public void defaultDataGenerator() {
        //Default main categories
        Category meat = new Category("Meat", QuantityUnit.GRAMS);
        mainCategories.add(meat);

        Category coldcuts = new Category("Cold Cuts", QuantityUnit.PIECES);
        mainCategories.add(coldcuts);

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
        Category dough = new Category("Dough", QuantityUnit.GRAMS, bakery);
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
        Category tuna = new Category("Tuna", QuantityUnit.GRAMS, meat);
        Category beef = new Category("Beef", QuantityUnit.GRAMS, meat);
        Category sausage = new Category("Sausage", QuantityUnit.PIECES, meat);
        Category steak = new Category("Steak", QuantityUnit.GRAMS, meat);
        Category chicken = new Category("Chicken", QuantityUnit.GRAMS, meat);
        Category bacon = new Category("Bacon", QuantityUnit.GRAMS, meat);
        Category ribs = new Category("Ribs", QuantityUnit.GRAMS, meat);

        Category salami = new Category("Salami", QuantityUnit.GRAMS, coldcuts);
        Category ham = new Category("Ham", QuantityUnit.GRAMS, coldcuts);
        Category pastrami = new Category("Pastrami", QuantityUnit.GRAMS, coldcuts);
        Category bologna = new Category("Bologna", QuantityUnit.GRAMS, coldcuts);
        Category pepperoni = new Category("Pepperoni", QuantityUnit.GRAMS, coldcuts);
        Category roastbeef = new Category("Roast Beef", QuantityUnit.GRAMS, coldcuts);

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
        Category nori = new Category("Nori", QuantityUnit.PIECES, misc);
        Category noodles = new Category("Noodles", QuantityUnit.PIECES, misc);
        Category bakingApples = new Category("Baking Apples", QuantityUnit.GRAMS, misc);

        Category salt = new Category("Salt", QuantityUnit.GRAMS, spices);
        Category pepper = new Category("Pepper", QuantityUnit.GRAMS, spices);
        Category sugar = new Category("Sugar", QuantityUnit.GRAMS, spices);
        Category paprika = new Category("Paprika", QuantityUnit.GRAMS, spices);
        Category mint = new Category("Mint", QuantityUnit.GRAMS, spices);
        Category basil = new Category("Basil", QuantityUnit.GRAMS, spices);
        Category parsley = new Category("Parsley", QuantityUnit.GRAMS, spices);
        Category thyme = new Category("Thyme", QuantityUnit.GRAMS, spices);
        Category rosemary = new Category("Rosemary", QuantityUnit.GRAMS, spices);
        Category oregano = new Category("Oregano", QuantityUnit.GRAMS, spices);
        Category cumin = new Category("Cumin", QuantityUnit.GRAMS, spices);
        Category curryPowder = new Category("Curry Powder", QuantityUnit.GRAMS, spices);
        Category ginger = new Category("Ginger", QuantityUnit.GRAMS, spices);

        Category ketchup = new Category("Ketchup", QuantityUnit.PIECES, condiments);
        Category mayonaise = new Category("Mayonaise", QuantityUnit.PIECES, condiments);
        Category tomatoSauce = new Category("Tomato Sauce", QuantityUnit.PIECES, condiments);
        Category mustard = new Category("Mustard", QuantityUnit.PIECES, condiments);
        Category remoulade = new Category("Remoulade", QuantityUnit.PIECES, condiments);
        Category bbq = new Category("Barbecue Sauce", QuantityUnit.PIECES, condiments);
        Category soysauce = new Category("Soy Sauce", QuantityUnit.PIECES, condiments);
        Category fishsauce = new Category("Fish Sauce", QuantityUnit.PIECES, condiments);
        Category broth = new Category("Broth", QuantityUnit.MILLILITRES, condiments);

        //Default recipes
        recipes.add(new Recipe("Classic Burger", new FoodItem[]{
                new FoodItem(beef), new FoodItem(eggs), new FoodItem(onion),
                new FoodItem(bread), new FoodItem(garlic), new FoodItem(salt),
                new FoodItem(pepper), new FoodItem(buns), new FoodItem(lettuce),
                new FoodItem(tomato), new FoodItem(ketchup), new FoodItem(mayonaise)}));

        recipes.add(new Recipe("Caesar Salad", new FoodItem[]{
                new FoodItem(olive), new FoodItem(baguette), new FoodItem(oliveoil),
                new FoodItem(garlic), new FoodItem(lemon), new FoodItem(parmesan),
                new FoodItem(anchovies), new FoodItem(eggs), new FoodItem(pepper),
                new FoodItem(salt), new FoodItem(lettuce)}));

        recipes.add(new Recipe("Pizza", new FoodItem[]{
                new FoodItem(dough), new FoodItem(cheese), new FoodItem(tomatoSauce),
                new FoodItem(pepperoni), new FoodItem(oregano)}));

        recipes.add(new Recipe("Maki Rolls", new FoodItem[]{
                new FoodItem(cucumber), new FoodItem(rice), new FoodItem(nori),
                new FoodItem(tuna), new FoodItem(avocado)}));

        recipes.add(new Recipe("Lasagna", new FoodItem[]{
                new FoodItem(olive), new FoodItem(beef), new FoodItem(onion),
                new FoodItem(pepper), new FoodItem(garlic), new FoodItem(tomatoSauce),
                new FoodItem(tomato)}));

        recipes.add(new Recipe("Ramen", new FoodItem[]{
                new FoodItem(broth), new FoodItem(garlic), new FoodItem(soysauce),
                new FoodItem(sugar), new FoodItem(onion), new FoodItem(noodles),
                new FoodItem(eggs)}));

        recipes.add(new Recipe("Apple Pie", new FoodItem[]{
                new FoodItem(dough), new FoodItem(lemon), new FoodItem(bakingApples),
                new FoodItem(sugar), new FoodItem(butter), new FoodItem(eggs)}));

        recipes.add(new Recipe("Chicken Curry", new FoodItem[]{
            new FoodItem(chicken), new FoodItem(pepper), new FoodItem(vegetableoil),
            new FoodItem(curryPowder), new FoodItem(garlic), new FoodItem(onion),
            new FoodItem(ginger), new FoodItem(yogurt)}));
    }
}
