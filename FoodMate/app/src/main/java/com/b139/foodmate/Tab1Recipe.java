package com.b139.foodmate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab1Recipe extends Fragment {

    private ListView ingredientListView;
    private RecipeIngredientsAdapter ingredientsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1recipe, container, false);


        /*ListView recipeListView = (ListView) rootView.findViewById(R.id.SearchView_RecipeFinder);

        ArrayAdapter recipeArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.recipe_ingredient_listitem, DataManager.recipes);

        recipeListView.setAdapter(recipeArrayAdapter);*/

        ingredientListView = (ListView) rootView.findViewById(R.id.ListView_RecipeIngredients);

        ingredientsAdapter = new RecipeIngredientsAdapter(getActivity(), DataManager.recipes.get(0).getContents());

        ingredientListView.setAdapter(ingredientsAdapter);

        return rootView;
    }

}
