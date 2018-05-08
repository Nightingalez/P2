import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		this.contents = foodItems;
	}

	// Adds a food item to contents
	public void addFoodItem(FoodItem item)
	{
		contents.add(item);
	}

	// Adds multiple food items to contents
	public void addFoodItem(ArrayList<FoodItem> item)
	{
		contents.addAll(item);
	}

	// Removes a food item from contents
	public void removeFoodItem(FoodItem item)
	{
		contents.remove(item);
	}

	// Removes multiple food item from contents
	public void removeFoodItem(ArrayList<FoodItem> item)
	{
		contents.removeAll(item);
	}

	// Sorts the food item lists contents, based on sorting type
	public void sortList(SortingMethod sortingType)
	{
		switch (sortingType) {
		
			case ALPHABETICAL: contents.sort(String.CASE_INSENSITIVE_ORDER);
			System.out.print(arg0);
			break;
			
			// Fix sorting
			case CATEGORY: contents.sort(String.);
		}
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
