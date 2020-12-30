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
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.scopes.FragmentScoped;

@FragmentScoped
@AndroidEntryPoint
public class ProfileDetailFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProfileDetailsBinding fragmentProfileDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_details, container, false);
        View view = fragmentProfileDetailsBinding.getRoot();
        Datum userData = (Datum) getArguments().getSerializable("Datum");
        fragmentProfileDetailsBinding.setUser(userData);
        return view;
    }
}
