import java.awt.Color;

public class Category {
	private String name;
	private QuantityUnit unit;
	private Color color;
	private FoodItem[] subCategories;
	private FoodItem subcatagoryOf;
	
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
	
	
	public FoodItem[] getSubcategories()	{
		return subCategories;
	}
	
	// TODO Add proper return
	public FoodItem findSubcategory(String name) {
		return null;
	}
	
	
}