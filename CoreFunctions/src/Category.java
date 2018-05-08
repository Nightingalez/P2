
import java.util.ArrayList;

public class Category
{
	private static int highestKnownID = 0;
	private int id;
	private String name;
	private QuantityUnit unit;
	private ArrayList<Category> subcategories;
	private Category parentCatagory;

	// Constructor for a category, that has no parent category (i.e. main
	// categories), and automatically assign an ID.
	public Category(String name, QuantityUnit unit)
	{
		this.name = name;
		this.unit = unit;
	}

	// Constructor for a category, that has a parent category (i.e. subcategories)
	public Category(String name, QuantityUnit unit, Category parentCategory)
	{
		this(name, unit);
		this.parentCatagory = parentCategory;
	}

	// Returns the category's ID
	public int getID()
	{
		return id;
	}

	// Returns the name of the category
	public String getName()
	{
		return name;
	}

	// Changes the name of the category
	public void setName(String name)
	{
		this.name = name;
	}

	// Adds a category as a subcategory of this category, and automatically set this
	// category as its parent category
	public void addSubcategory(Category subcategory)
	{
		subcategories.add(subcategory);
		subcategory.setParentCategory(this);
	}

	// Finds and removes a subcategory from this category's ArrayList, and unparents
	// it. If the given
	// category cannot be found, do noting
	public void removeSubcategory(Category subcategory)
	{
		if (subcategories.remove(subcategory))
		{
			subcategory.setParentCategory(null);
		}
	}

	// Returns the entire list of this category's subcategories
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

	public void setParentCategory(Category parentCatagory)
	{
		this.parentCatagory = parentCatagory;
	}

	// Search through this category's list of subcategory, and returns a subcategory
	// with a matching name. Return null if no category of that name exists.
	public Category findSubcategory(String name)
	{
		for (Category c : subcategories)
		{
			if (c.getName().toLowerCase() == name.toLowerCase())
			{
				return c;
			}
		}
		return null;
	}

	// Returns true if the category has no parent category
	public boolean isMainCategory()
	{
		return parentCatagory == null;
	}

	// Generate a new ID for this category. NOTE: ID's are only need when saving
	public void generateID()
	{
		this.id = highestKnownID + 1;
		highestKnownID += 1;
	}
}