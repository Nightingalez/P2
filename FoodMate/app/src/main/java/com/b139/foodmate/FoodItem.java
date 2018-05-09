package com.b139.foodmate;
import java.util.Comparator;

public class FoodItem
{
	private static int highestKnownID = 0;
	private int id;

	private int amount;
	private Category category;
	private FoodItemList inList;

	// Create a food item of a specific type (category) TODO: Add to class diagram
	public FoodItem(Category category)
	{
		this.category = category;
		generateID();
	}

	// Create a food item of a specific type (category), in a specific list (inList)
	public FoodItem(Category category, FoodItemList inList)
	{
		this(category);
		this.inList = inList;
	}

	// Create a food item with an amount, of a specific type (category)
	public FoodItem(int amount, Category category)
	{
		this(category);
		this.amount = amount;
	}

	// Create a food item with an amount, of a specific type (category), in a
	// specific list (inList)
	public FoodItem(int amount, Category category, FoodItemList inList)
	{
		this(category, inList);
		this.amount = amount;
	}

	// Returns the name of the food item, derived from its category
	public String getName()
	{
		return category.getName();
	}
	
	public Category getCategory()
	{
		return category;
	}

	// Returns the amount of the food item
	public int getAmount()
	{
		return amount;
	}

	// Sets the amount of the food item
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	// Returns what list this food item is in
	public FoodItemList getInList()
	{
		return inList;
	}

	// Sets what list this food item is in
	public void setInList(FoodItemList list)
	{
		this.inList = list;
	}

	// TODO: add new methods to class diagram
	public Category getMainCategory()
	{
		return category.getMainCategory();
	}
	
	// Creates a clone of this food item. NOTE: it does not inherit which list it exists in.
	public FoodItem clone()
	{
		return new FoodItem(amount, category);
	}

	// Generate a new ID for this food item
	public void generateID()
	{
		this.id = highestKnownID + 1;
		highestKnownID += 1;
	}

	// Returns the category's ID
	public int getID()
	{
		return id;
	}

	/* Comparators for sorting */
	// Comparator for name
	public static Comparator<FoodItem> FoodNameComparator = new Comparator<FoodItem>()
	{
		public int compare(FoodItem f1, FoodItem f2)
		{
			String itemName1 = f1.getName().toUpperCase();
			String itemName2 = f2.getName().toLowerCase();

			// Ascending order
			return itemName1.compareTo(itemName2);
		}
	};

	// Comparator for main category name
	public static Comparator<FoodItem> MainCatagoryNameComparator = new Comparator<FoodItem>()
	{
		public int compare(FoodItem f1, FoodItem f2)
		{
			String itemName1 = f1.getMainCategory().getName().toUpperCase();
			String itemName2 = f2.getMainCategory().getName().toUpperCase();

			// Ascending order
			return itemName1.compareTo(itemName2);
		}
	};
}
