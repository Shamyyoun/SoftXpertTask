package com.example.softxpert.ui.cars;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.softxpert.R;
import com.example.softxpert.data.models.entities.Car;
import com.example.softxpert.databinding.ItemCarBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {
    private List<Car> cars;

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemCarBinding dataBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_car, viewGroup, false);
        return new CarViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder viewHolder, int i) {
        Car car = cars.get(i);
        viewHolder.dataBinding.setCar(car);
    }

    @Override
    public int getItemCount() {
        return cars != null ? cars.size() : 0;
    }

    public void setCarsList(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    public void addCars(List<Car> cars) {
        this.cars.addAll(cars);
        notifyDataSetChanged();
    }

    class CarViewHolder extends RecyclerView.ViewHolder {
        private ItemCarBinding dataBinding;

        public CarViewHolder(@NonNull ItemCarBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }
    }
}