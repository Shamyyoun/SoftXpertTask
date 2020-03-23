package com.example.softxpert.di.modules;

import com.example.softxpert.di.ViewModelKey;
import com.example.softxpert.ui.ViewModelFactory;
import com.example.softxpert.ui.cars.CarsViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CarsViewModel.class)
    abstract ViewModel bindCarsViewModel(CarsViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory factory);

}
