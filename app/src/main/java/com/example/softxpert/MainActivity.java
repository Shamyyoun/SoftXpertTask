package com.example.softxpert;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.softxpert.app.MyApp;
import com.example.softxpert.data.models.User;
import com.example.softxpert.databinding.ActivityMainBinding;
import com.example.softxpert.ui.main.MainViewModel;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding dataBinding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ((MyApp) getApplication()).getAppComponent().inject(this);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);

        observe();

        loadUser();
    }

    private void loadUser() {
        dataBinding.setShowLoading(true);
        dataBinding.setShowMainView(false);

        viewModel.loadData();
    }

    private void observe() {
        // user live data
        viewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                dataBinding.setShowMainView(true);
                dataBinding.setShowLoading(false);

                dataBinding.setUser(user);

                Log.d(TAG, "UserChanged");
            }
        });

        // error live data
        viewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                dataBinding.setShowLoading(false);

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                Log.d(TAG, "Error: " + message);
            }
        });
    }
}
