
public class FoodItem {

	private int amount;
	private Category category;
	private FoodItemList inList;
	
	//Create a food item of a specific type (category), in a specific list (inList)
	public FoodItem(Category category, FoodItemList inList) {
		this.category = category;
		this.inList = inList;

	}
	
	//As before, but sets a specific amount
	public FoodItem(int amount, Category category, FoodItemList inList) {
		this.amount = amount;
		this.category = category;
		this.inList = inList;
		 
	}
	
	//Returns the name of the food item, derived from its category
	public String getName() {
		return category.getName();
	}
	
	//Returns the amount of the food item
	public int getAmount() {
		return amount;
	}
	
	//Sets the amount of the food item
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	//Returns what list this food item is in
	public FoodItemList getInList() {
		return inList;
	}
	
	//Sets what list this food item is in
	public void setInList(FoodItemList list) {
		this.inList = list;
	}
}
