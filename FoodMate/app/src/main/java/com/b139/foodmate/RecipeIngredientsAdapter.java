package com.b139.foodmate;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeIngredientsAdapter extends ArrayAdapter<FoodItem> {

    private ArrayList<FoodItem> ingredients;
    Context context;

    public RecipeIngredientsAdapter(Context context, ArrayList<FoodItem> foodItems) {
        super(context, R.layout.recipe_ingredient_listitem, foodItems);
        this.ingredients = foodItems;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        FoodItem foodItem = getItem(position);

        // Declare a holder
        IngredientViewHolder holder = null;

        final View result;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            //Create a viewHolder
            holder = new IngredientViewHolder();

            //Inflate
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.recipe_ingredient_listitem, parent, false);

            //Populate holder
            holder.circle = (GradientDrawable) convertView.findViewById(R.id.inStorageDot).getBackground();
            holder.IngredientName = (TextView) convertView.findViewById(R.id.listIngredientName);
            holder.quantity = (TextView) convertView.findViewById(R.id.quantity);

            result = convertView;

            convertView.setTag(holder);
        } else {
            //If already inflated, retrieve the existing holder
            holder = (IngredientViewHolder) convertView.getTag();
            result = convertView;
        }


        // Populate the data into the template view using the data object
        Boolean isInStorage = DataManager.isInStorage(foodItem.getCategory());
        holder.circle.setColor(getContext().getColor(isInStorage ? R.color.InStorage : R.color.NotInStorage));

        holder.IngredientName.setText(foodItem.getName());

        if (foodItem.getAmount() != 0) {
            String toDisplay = String.valueOf(foodItem.getAmount()) + " " + foodItem.getUnitAbbreviation();
            holder.quantity.setText(toDisplay);
        } else {
            holder.quantity.setText("");
        }


        // Return the completed view to render on screen
        return convertView;
    }


    static class IngredientViewHolder {
        GradientDrawable circle;
        private TextView IngredientName;
        private TextView quantity;
    }
}
