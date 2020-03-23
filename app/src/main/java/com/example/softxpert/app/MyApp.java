package com.example.softxpert.app;

import android.app.Application;

import com.example.softxpert.di.components.AppComponent;
import com.example.softxpert.di.components.DaggerAppComponent;

public class MyApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
