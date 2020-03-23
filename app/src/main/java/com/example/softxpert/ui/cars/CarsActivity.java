package com.example.softxpert.ui.cars;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.softxpert.R;
import com.example.softxpert.app.MyApp;
import com.example.softxpert.data.models.entities.Car;
import com.example.softxpert.databinding.ActivityCarsBinding;
import com.example.softxpert.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CarsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "CarsActivity";

    private ActivityCarsBinding dataBinding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CarsViewModel viewModel;

    private CarsAdapter carsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_cars);
        ((MyApp) getApplication()).getAppComponent().inject(this);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(CarsViewModel.class);

        // config views
        dataBinding.swipeLayout.setOnRefreshListener(this);
        configRecyclerView();

        observe();

        loadCars();
    }

    private void configRecyclerView() {
        // config recycler view
        RecyclerView recyclerView = dataBinding.rvCars;
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        carsAdapter = new CarsAdapter();
        recyclerView.setAdapter(carsAdapter);

        // add load more listener
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (Utils.isReachedEndOfRecycler(layoutManager)) {
                    // load more
//                    loadMoreCars(); TODO: check bug
                }
            }
        });
    }

    private void observe() {
        // cars live data
        viewModel.getCarsLiveData().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars) {
                dataBinding.setShowMainView(true);
                dataBinding.setShowLoading(false);

                carsAdapter.setCarsList(cars);

                Log.d(TAG, "Cars Changed");
            }
        });

        // more cars live data
        viewModel.getMoreCarsLiveData().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars) {
                carsAdapter.addCars(cars);

                Log.d(TAG, "Cars Added");
            }
        });

        // error live data
        viewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                dataBinding.setShowLoading(false);

                Toast.makeText(CarsActivity.this, message, Toast.LENGTH_SHORT).show();

                Log.d(TAG, "Error: " + message);
            }
        });
    }

    private void loadCars() {
        dataBinding.setShowLoading(true);
        dataBinding.setShowMainView(false);

        viewModel.loadCars();
    }

    private void loadMoreCars() {
        viewModel.loadMoreCars();
    }

    @Override
    public void onRefresh() {
        // update views
        dataBinding.setShowLoading(true);
        dataBinding.setShowMainView(false);
        dataBinding.swipeLayout.setRefreshing(false);

        viewModel.refresh();
    }
}
