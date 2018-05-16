package com.b139.foodmate;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Tab1Recipe extends Fragment {

    private ListView ingredientListView;
    private RecipeIngredientsAdapter ingredientsAdapter;

    private ListView recipesListView;
    private RecipeAdapter recipeAdapter;

    SearchView recipeSearchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1recipe, container, false);

        recipeSearchView = rootView.findViewById(R.id.SearchView_RecipeFinder);
        ingredientListView = (ListView) rootView.findViewById(R.id.ListView_RecipeIngredients);
        recipesListView = (ListView) rootView.findViewById(R.id.ListView_RecipeSearch);
        recipesListView.setClickable(true);

        recipeSearchView.setOnClickListener(new SearchView.OnClickListener() {
            public void onClick(View v) {
                SearchView searchView = (SearchView) v;
                searchView.setIconified(false);
            }
        });


        //Showing ingredients
        ingredientsAdapter = new RecipeIngredientsAdapter(getActivity(), new ArrayList<FoodItem>(DataManager.ActiveRecipe.getContents()));
        //Note: we create a new arrayList from food items, as the adapter needs to maintain connection to a list. It will internally remember this copy.
        ingredientListView.setAdapter(ingredientsAdapter);

        //Recipe search
        recipeAdapter = new RecipeAdapter(getContext(), new ArrayList<Recipe>(DataManager.recipes));
        //Note: we create a new arrayList from recipes, as the adapter needs to maintain connection to a list. It will internally remember this copy.
        recipesListView.setAdapter(recipeAdapter);

        //recipesListView.setAdapter(new ArrayAdapter<String>(this, R.layout.recipe_listitem, recipesListView));
        recipesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                DataManager.ActiveRecipe = DataManager.filteredRecipes.get(position);
                ingredientsAdapter.updateList();
            }
        });

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
