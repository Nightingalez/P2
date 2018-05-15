package com.b139.foodmate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Tab1Recipe extends Fragment {

    private ListView ingredientListView;
    private RecipeIngredientsAdapter ingredientsAdapter;

    private ListView recipesListView;
    private RecipeAdapter recipeAdapter;

    private Recipe ActiveRecipe = DataManager.recipes.get(2);
    SearchView recipeSearchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1recipe, container, false);

        recipeSearchView = rootView.findViewById(R.id.SearchView_RecipeFinder);
        ingredientListView = (ListView) rootView.findViewById(R.id.ListView_RecipeIngredients);
        recipesListView = (ListView) rootView.findViewById(R.id.ListView_RecipeSearch);

        recipeSearchView.setOnClickListener(new SearchView.OnClickListener() {
            public void onClick(View v) {
                SearchView searchView = (SearchView) v;
                searchView.setIconified(false);
            }
        });


        //Showing ingredients
        ingredientsAdapter = new RecipeIngredientsAdapter(getActivity(), ActiveRecipe.getContents());
        ingredientListView.setAdapter(ingredientsAdapter);

        //Recipe search
        recipeAdapter = new RecipeAdapter(getContext(), new ArrayList<Recipe>(DataManager.recipes));
        recipesListView.setAdapter(recipeAdapter);

        recipeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recipeAdapter.filter(newText);
                return true;
            }
        });

        return rootView;
    }

}
