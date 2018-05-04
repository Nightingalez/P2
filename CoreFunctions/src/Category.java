import java.awt.Color;

public class Category {
	private String name;
	private QuantityUnit unit;
	private Color color;
	private FoodItem[] subcategories;
	private FoodItem subcatagoryOf;
	
	public Category()	{
	}
	
	public Category(String name, QuantityUnit unit)	{
	}
	
	public Category(String name, QuantityUnit unit, Category subcatagoryOf)	{
	}
	
	public String getName()	{
		return name;
	}
	
	public void setName()	{
	}
	
	public void addSubcategory(FoodItem subcategory) {
	}
	
	public void removeSubcategory(FoodItem subcategory) {
		
	}
	
	public FoodItem[] getSubcategories()	{
		return subcategories;
	}
	
	public FoodItem getParentCategory() {
		return subcatagoryOf;
	}
	
	// TODO Add proper return
	public FoodItem findSubcategory(String name) {
		return null;
	}
	
	
}