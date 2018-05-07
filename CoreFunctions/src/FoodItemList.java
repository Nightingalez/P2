import java.util.ArrayList;

public class FoodItemList
{

	private ArrayList<FoodItem> contents;

	// Constructs an empty food item list
	public FoodItemList()
	{
	}

	// Constructs a food item list with contents
	public FoodItemList(ArrayList<FoodItem> foodItems)
	{
	}

	// Adds a food item to contents
	public void addFoodItem(FoodItem item)
	{
	}

	// Adds multiple food items to contents
	public void addFoodItem(ArrayList<FoodItem> item)
	{

	}

	// Removes a food item from contents
	public void removeFoodItem(FoodItem item)
	{

	}

	// Removes multiple food item from contents
	public void removeFoodItem(ArrayList<FoodItem> item)
	{

	}

	// Sorts the food item lists contents, based on sorting type
	public void sortList(SortingMethod sortingType)
	{
	}

	// Compare the contents of this food item list with another. Return what is
	// missing from this list
	public FoodItemList findMissingItemsInList(FoodItemList comparedList)
	{
		return null;
		// TODO Add proper return
	}

	// Compare the contents of two food item lists. Return what is missing from the
	// first list
	public static FoodItemList findMissingItemsInList(FoodItemList firstList, FoodItemList comparedList)
	{
		return null;
		// TODO Add proper return
	}
}
