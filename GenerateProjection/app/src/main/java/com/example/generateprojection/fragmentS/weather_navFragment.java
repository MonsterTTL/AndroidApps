package com.example.generateprojection.fragmentS;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.generateprojection.R;
import com.example.generateprojection.viewmodel.weatherViewModel;


public class weather_navFragment extends Fragment {

    public weatherViewModel model;
    public weather_navFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_nav, container, false);
        model = new ViewModelProvider(requireActivity()).get(weatherViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model.requireData();
    }
}