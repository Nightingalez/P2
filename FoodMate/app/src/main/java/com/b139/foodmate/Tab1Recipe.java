package com.b139.foodmate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tab1Recipe extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1recipe, container, false);

        /*ListView recipeListView = (ListView) rootView.findViewById(R.id.SearchView_RecipeFinder);

        ArrayAdapter recipeArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.recipe_view, DataManager.recipes);

        recipeListView.setAdapter(recipeArrayAdapter);*/

        ListView ingredientListView = (ListView) rootView.findViewById(R.id.ListView_RecipeIngredients);

        ArrayAdapter ingredientArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.recipe_view, DataManager.recipes.get(0).getContents());

        ingredientListView.setAdapter(ingredientArrayAdapter);

        return rootView;
    }

}
