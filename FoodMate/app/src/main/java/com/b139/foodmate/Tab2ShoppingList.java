package com.b139.foodmate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab2ShoppingList extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2shoppinglist, container, false);

        ListView shoppingListView = (ListView) rootView.findViewById(R.id.ListView_Shopping);

        ArrayAdapter shoppingArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_view, DataManager.shoppingList.getContents());

        shoppingListView.setAdapter(shoppingArrayAdapter);

        return rootView;
    }
}
