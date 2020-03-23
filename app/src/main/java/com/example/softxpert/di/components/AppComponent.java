package com.example.softxpert.di.components;

import com.example.softxpert.di.modules.ContextModule;
import com.example.softxpert.di.modules.NetworkModule;
import com.example.softxpert.ui.cars.CarsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {

    void inject(CarsActivity mainActivity);
}
