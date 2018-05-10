package com.b139.foodmate;

import android.app.Application;
public class Startup extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        DataManager.generateDummyData();
    }
}