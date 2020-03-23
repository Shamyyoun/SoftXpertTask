package com.example.softxpert.ui.cars;

import com.example.softxpert.data.models.entities.Car;
import com.example.softxpert.data.models.responses.CarsResponse;
import com.example.softxpert.data.repositories.CarRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CarsViewModel extends ViewModel {
    private CarRepository carRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<List<Car>> carsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Car>> moreCarsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private int pageNumber = 1;

    @Inject
    public CarsViewModel(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    private void loadData(final int page) {
        disposable.add(carRepository.getCars(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CarsResponse>() {
                    @Override
                    public void onSuccess(CarsResponse carsResponse) {
                        // validate response
                        if (!carsResponse.isSuccessful()) {
                            // post error
                            String error = carsResponse.getError() != null ? carsResponse.getError().getMessage() : null;
                            errorLiveData.setValue(error);
                        }

                        // post cars to suitable live data
                        if (pageNumber == 1) {
                            // first time to load data
                            carsLiveData.setValue(carsResponse.getData());
                        } else {
                            // more data
                            moreCarsLiveData.setValue(carsResponse.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(e.getMessage());
                    }
                }));
    }

    public void loadCars() {
        loadData(pageNumber);
    }

    public void loadMoreCars() {
        loadData(++pageNumber);
    }

    public void refresh() {
        pageNumber = 1;
        loadData(pageNumber);
    }

    public MutableLiveData<List<Car>> getCarsLiveData() {
        return carsLiveData;
    }

    public MutableLiveData<List<Car>> getMoreCarsLiveData() {
        return moreCarsLiveData;
    }

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
        }
    }
}
