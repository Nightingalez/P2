package com.b139.foodmate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class Tab1Recipe extends Fragment {

    private ListView ingredientListView;
    private RecipeIngredientsAdapter ingredientsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1recipe, container, false);

        rootView.findViewById(R.id.SearchView_RecipeFinder).setOnClickListener(new SearchView.OnClickListener() {
            public void onClick(View v) {
                SearchView searchView = (SearchView) v;
                searchView.setIconified(false);
            }
        });

        //Showing ingredients
        ingredientListView = (ListView) rootView.findViewById(R.id.ListView_RecipeIngredients);
        ingredientsAdapter = new RecipeIngredientsAdapter(getActivity(), DataManager.recipes.get(2).getContents());
        ingredientListView.setAdapter(ingredientsAdapter);

        return rootView;
    }

}
