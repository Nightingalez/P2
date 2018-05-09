import java.util.ArrayList;

public class FunctionTest
{
	public static void main(String[] args)
	{
		// Create 3 main categories, 2 subcategories
		Category veg = new Category("Vegetable", QuantityUnit.GRAMS);
		Category cuc = new Category("Cucumber", QuantityUnit.GRAMS, veg);

		Category mea = new Category("Meat", QuantityUnit.GRAMS);
		Category bac = new Category("Bacon", QuantityUnit.GRAMS, mea);

		Category dai = new Category("Dairy", QuantityUnit.MILLILITRES);

		// Search for a subcategory by name
		Category src = mea.findSubcategory("Bacon");
		System.out.println(src.getName());

		System.out.println("");

		// Create a list with 1 food item
		FoodItem testItem = new FoodItem(veg);
		FoodItemList firstList = new FoodItemList();

		firstList.addFoodItem(testItem);

		// Add 2 more food items
		ArrayList<FoodItem> testItems = new ArrayList<FoodItem>();
		testItems.add(new FoodItem(bac));
		testItems.add(new FoodItem(dai));

		firstList.addFoodItem(testItems);

		// Print the names of the food items in the list
		for (FoodItem f : firstList.getContents())
		{
			System.out.println(f.getName());
		}

		System.out.println("");

		// Remove 1 food item
		firstList.removeFoodItem(testItem.getID());

		for (FoodItem f : firstList.getContents())
		{
			System.out.println(f.getName());
		}

		System.out.println("");

		// Create a recipe with 2 food items
		Recipe res = new Recipe();
		res.addFoodItem(new FoodItem(cuc));
		res.addFoodItem(new FoodItem(bac));

		// Compare the list and the recipe, and see what is missing
		FoodItemList missing = firstList.findMissingItemsInList(res);

		for (FoodItem f : missing.getContents())
		{
			System.out.println(f.getName());
		}

		System.out.println("");

		// Sort the different lists
		// Unsorted recipe
		for (FoodItem f : res.getContents())
		{
			System.out.println(f.getName());
		}
		System.out.println("");

		res.sortList(SortingMethod.ALPHABETICAL);
		// sorted recipe by item name
		for (FoodItem f : res.getContents())
		{
			System.out.println(f.getName());
		}
		System.out.println("");

		// Unsorted firstlist
		for (FoodItem f : firstList.getContents())
		{
			System.out.println(f.getName());
		}
		System.out.println("");

		firstList.sortList(SortingMethod.CATEGORY);

		// sorted recipe by item main category name
		for (FoodItem f : firstList.getContents())
		{
			System.out.println(f.getName());
		}
		System.out.println("");
		

		// Generate IDs for Categories
		veg.generateID();
		cuc.generateID();
		mea.generateID();
		bac.generateID();
		dai.generateID();

		System.out.println(veg.getID());
		System.out.println(cuc.getID());
		System.out.println(mea.getID());
		System.out.println(bac.getID());
		System.out.println(dai.getID());

	}
}
