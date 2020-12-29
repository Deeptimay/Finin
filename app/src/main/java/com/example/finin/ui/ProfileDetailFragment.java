package com.example.finin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finin.R;
import com.example.finin.databinding.FragmentProfileDetailsBinding;
import com.example.finin.models.Datum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

public class ProfileDetailFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentProfileDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_details, container, false);
        View view = binding.getRoot();
        Datum userData = (Datum) getArguments().getSerializable("Datum");
        binding.setUser(userData);
        return view;
    }
}