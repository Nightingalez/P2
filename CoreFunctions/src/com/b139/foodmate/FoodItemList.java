package com.b139.foodmate;

import java.util.ArrayList;

public class FoodItemList {

    private ArrayList<FoodItem> contents = new ArrayList<FoodItem>();

    // Constructs an empty food item list
    public FoodItemList() {
    }

    // Constructs a food item list with contents from an array list
    public FoodItemList(ArrayList<FoodItem> foodItems) {
        addFoodItem(foodItems);
    }

    // Constructs a food item list with contents from an array
    public FoodItemList(FoodItem[] foodItems) {
        addFoodItem(foodItems);
    }

    // Returns the contents (ArrayList) of food items
    public ArrayList<FoodItem> getContents() {
        return contents;
    }

    // Adds a food item to contents
    public void addFoodItem(FoodItem item) {
        contents.add(item);
        item.setInList(this);
    }

    // Adds a list of food items to contents
    public void addFoodItem(ArrayList<FoodItem> item) {
        contents.addAll(item);
        for (FoodItem f : item) {
            f.setInList(this);
        }
    }

    // Adds an array of food items to contents
    public void addFoodItem(FoodItem[] item) {
        for (FoodItem f : item) {
            contents.add(f);
            f.setInList(this);
        }
    }

    // Removes a food item from contents
    public void removeFoodItem(FoodItem item) {
        contents.remove(item);
    }

    // Removes multiple food item from contents by ArrayList
    public void removeFoodItem(ArrayList<FoodItem> item) {
        contents.removeAll(item);
    }
    
 // Removes multiple food item from contents by Array
    public void removeFoodItem(FoodItem[] item) {
    	for (FoodItem f : item)
    	{
    		this.removeFoodItem(f);
    	}
    }

    // Removes a food item by ID
    public void removeFoodItem(int id) {
        for (FoodItem f : contents) {
            if (f.getID() == id) {
                contents.remove(f);
                break;
            }
        }
    }

    // Removes food items by IDs
    public void removeFoodItem(int[] id) {
        for (int i : id) {
            for (FoodItem f : contents) {
                if (f.getID() == i) {
                    contents.remove(f);
                    break;
                }
            }
        }
    }

    //Checks if the list contains an item of the given category
    public boolean categoryIsInList(Category category) {
        for (FoodItem item : contents) {
            if (item.getCategory() == category) {
                return true;
            }
        }
        return false;
    }

    // Sorts the food item lists contents, based on sorting type
    public void sortList(SortingMethod sortingType) {
        switch (sortingType) {
            case ALPHABETICAL:
                contents.sort(FoodItem.FoodNameComparator);
                break;

            case CATEGORY:
                contents.sort(FoodItem.MainCategoryNameComparator);
                break;
        }
    }

    // Compare the contents of this food item list with another. Return what is
    // missing from this list
    public FoodItemList findMissingItemsInList(FoodItemList comparedList) {
        return findMissingItemsInList(this, comparedList);
    }

    // Compare the contents of two food item lists. Return what is missing from the
    // first list
    public static FoodItemList findMissingItemsInList(FoodItemList firstList, FoodItemList comparedList) {
        FoodItemList missing = new FoodItemList();

        for (FoodItem c : comparedList.getContents()) {
            boolean found = firstList.categoryIsInList(c.getCategory());

            if (!found) {
                missing.addFoodItem(c.clone());
            }
        }

        return missing;

    }
}
