package com.example.softxpert.di.components;

import com.example.softxpert.MainActivity;
import com.example.softxpert.di.modules.ContextModule;
import com.example.softxpert.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}
