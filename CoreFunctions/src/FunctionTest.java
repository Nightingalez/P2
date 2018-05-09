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

		// Remove 1 food item

		// Create a recipe with 2 food items

		// Compare the list and the recipe, and see what is missing

		// Generate IDs for Categories

	}

}
