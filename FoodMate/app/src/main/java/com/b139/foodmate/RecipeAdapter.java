package com.b139.foodmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    private Context context;
    private ArrayList<Recipe> filteredRecipes;

    public RecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        super(context, R.layout.recipe_listitem, recipes);
        this.filteredRecipes = recipes;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Recipe recipe = getItem(position);

        // Declare a holder
        RecipeViewHolder holder;

        final View result;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            //Create a viewHolder
            holder = new RecipeViewHolder();

            //Inflate
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.recipe_listitem, parent, false);

            //Populate holder
            holder.recipeName = (TextView) convertView.findViewById(R.id.listRecipeName);

            result = convertView;

            convertView.setTag(holder);
        } else {
            //If already inflated, retrieve the existing holder
            holder = (RecipeViewHolder) convertView.getTag();
            result = convertView;
        }

        // Populate the data into the template view using the data object
        holder.recipeName.setText(recipe.getName());

        // Return the completed view to render on screen
        return convertView;
    }

    public void filter(String text) {
        text = text.toLowerCase();
        filteredRecipes.clear();

        if (text.length() == 0) {
            filteredRecipes.addAll(DataManager.recipes);
        } else {
            String name;
            for (Recipe recipe : DataManager.recipes) {
                name = recipe.getName().toLowerCase();

                if (name.contains(text)) {
                    filteredRecipes.add(recipe);
                }
            }
        }

        this.notifyDataSetChanged();
    }

    public void addItem(Recipe recipe) {
        DataManager.recipes.add(recipe);
        filteredRecipes.add(recipe);
    }


    static class RecipeViewHolder {
        private TextView recipeName;
    }
}