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

    private ArrayList<FoodItem> dataSet;
    Context mContext;

    public RecipeIngredientsAdapter(Context context, ArrayList<FoodItem> foodItems) {
        super(context, R.layout.recipe_ingredient_listitem, foodItems);
        this.dataSet = foodItems;
        this.mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        FoodItem foodItem = getItem(position);

        // Declare a holder
        ViewHolder holder = null;

        final View result;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            //Create a viewHolder
            holder = new ViewHolder();

            //Inflate
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.recipe_ingredient_listitem, parent, false);

            //Populate holder
            holder.circle = (GradientDrawable) convertView.findViewById(R.id.inStorageDot).getBackground();
            holder.ingredientName = (TextView) convertView.findViewById(R.id.ingredientName);
            holder.quantity = (TextView) convertView.findViewById(R.id.quantity);

            result = convertView;

            convertView.setTag(holder);
        } else {
            //If already inflated, retrieve the existing holder
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        // Populate the data into the template view using the data object
        Boolean isInStorage = DataManager.isInStorage(foodItem.getCategory());
        holder.circle.setColor(getContext().getColor(R.color.InStorage));

        holder.ingredientName.setText(foodItem.getName());

        if (foodItem.getAmount() != 0) {
            String toDisplay = String.valueOf(foodItem.getAmount()) + " " + foodItem.getUnitAbbreviation();
            holder.quantity.setText(toDisplay);
        } else {
            holder.quantity.setText("");
        }


        // Return the completed view to render on screen
        return convertView;
    }


    static class ViewHolder {
        GradientDrawable circle;
        private TextView ingredientName;
        private TextView quantity;
    }
}
