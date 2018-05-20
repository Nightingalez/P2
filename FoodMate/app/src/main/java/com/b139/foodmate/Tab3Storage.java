package com.b139.foodmate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab3Storage extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3storage, container, false);

        ListView storageListView = (ListView) rootView.findViewById(R.id.ListView_Storage);

        ArrayAdapter storageArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_view, DataManager.storage.getContents());

        storageListView.setAdapter(storageArrayAdapter);

        return rootView;
    }
}
