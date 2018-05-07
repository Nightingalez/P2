import java.awt.Color;
import java.util.ArrayList;

public class Category
{
	private String name;
	private QuantityUnit unit;
	private Color color; //TODO: discuss whether we should keep this, due to android color definitions
	private ArrayList<Category> subcategories;
	private Category parentCatagory;

	// Constructor for a category, that has no parent category (i.e. main
	// categories)
	public Category(String name, QuantityUnit unit)
	{
	}

	// Constructor for a category, that has a parent category (i.e. subcategories)
	public Category(String name, QuantityUnit unit, Category subcategoryOf)
	{
	}

	// Returns the name of the category
	public String getName()
	{
		return name;
	}

	// Changes the name of the category
	public void setName()
	{
	}

	// Adds a category as a subcategory of this category
	public void addSubcategory(Category subcategory)
	{
	}

	// Finds and removes a subcategory from this category's ArrayList. If the given
	// category cannot be found, do noting
	public void removeSubcategory(Category subcategory)
	{

	}

	// Returns the entire list of this category's subcatagories
	public ArrayList<Category> getSubcategories()
	{
		return subcategories;
	}

	// Returns this catagory's parent category, if any. Should return null if no
	// parent category exists
	public Category getParentCategory()
	{
		return parentCatagory;
	}

	// Search through this category's list of subcategory, and returns a subcategory
	// with a matching name. Return null if no category of that name exists.
	public Category findSubcategory(String name)
	{
		// TODO Add proper return
		return null;
	}
}