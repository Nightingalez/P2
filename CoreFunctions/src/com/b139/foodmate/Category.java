package com.b139.foodmate;
import java.util.ArrayList;

public class Category
{
	private static int highestKnownID = 0;
	private int id;
	private String name;
	private QuantityUnit unit;
	private ArrayList<Category> subcategories = new ArrayList<Category>();
	private Category parentCategory;

	// Constructor for a category, that has no parent category (i.e. main
	// categories), and automatically assign an ID.
	public Category(String name, QuantityUnit unit)
	{
		this.name = name;
		this.unit = unit;
	}

	// Constructor for a category, that has a parent category (i.e. subcategories)
	public Category(String name, QuantityUnit unit, Category newParentCategory)
	{
		this(name, unit);
		newParentCategory.addSubcategory(this);
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

	//Returns the unit type 
	public QuantityUnit getUnit() {
		return unit;
	}

	//Returns the units text abbrevation
	public String getUnitAbbreviation()
	{
		return unit.toString();
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

	//returns this category, all subcategories, subcategories of those, etc., as a single list
	public ArrayList<Category> getCategoryBranch()
	{
		ArrayList<Category> result = new ArrayList<Category>();
		result.add(this);
		for (Category c : subcategories)
		{
			result.addAll(c.getCategoryBranch());
		}
		return result;
	}

	//Returns whether or not this category has subcategories
	public boolean hasSubcategories()
	{
		if(subcategories == null || subcategories.size() == 0)
		{
			return false;
		}
		return true;
	}

	// Returns this categories parent category, if any. Should return null if no
	// parent category exists
	public Category getParentCategory()
	{
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory)
	{
		this.parentCategory = parentCategory;
	}
	
	//Finds the uppermost main category that this category belongs under. Returns itself if it is a main category
		public Category getMainCategory()
		{
			if (!this.isMainCategory())
			{
				return parentCategory.getMainCategory();
			}
			else
			{
				return this;
			}
		}

	// Search through this category's list of subcategories, and returns a subcategory
	// with a matching name. Return null if no category of that name exists.
	public Category findSubcategory(String name)
	{
		for (Category c : subcategories)
		{
			if (c.getName().equalsIgnoreCase(name))
			{
				return c;
			}
		}
		return null;
	}

	// Returns true if the category has no parent category
	public boolean isMainCategory()
	{
		return parentCategory == null;
	}

	// Generate a new ID for this category. NOTE: ID's are only need when loading and saving
	public void generateID()
	{
		this.id = highestKnownID + 1;
		highestKnownID += 1;
	}

	// Returns the category's ID
	public int getID()
	{
		return id;
	}

	// Sets the category's ID
	public void setID(int id)
	{
		this.id = id;
	}
}