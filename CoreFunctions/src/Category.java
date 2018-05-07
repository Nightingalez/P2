import java.awt.Color;
import java.util.ArrayList;

public class Category {
	private String name;
	private QuantityUnit unit;
	private Color color;
	private ArrayList<FoodItem> subcategories;
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
	
	public void removeSubcategory(FoodItem subcategory) {
		
	}
	
	public ArrayList<FoodItem> getSubcategories()	{
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