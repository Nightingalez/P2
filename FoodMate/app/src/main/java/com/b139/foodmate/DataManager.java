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
    private static ArrayList<Category> categories;
    final static String CATEGORIES = "categories.txt";

    private static FoodItemList shoppingList;
    final static String SHOPPING = "shopping.txt";

    private static FoodItemList storage;
    final static String STORAGE = "storage.txt";

    private static ArrayList<Recipe> recipes;
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
}
