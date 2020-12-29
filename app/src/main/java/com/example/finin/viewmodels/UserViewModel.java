package com.example.finin.viewmodels;


import android.app.Application;

import com.example.finin.models.UserResponse;
import com.example.finin.repositories.UserRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private final LiveData<UserResponse> usersList;
    UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();
        usersList = userRepository.loadUsers(1);
    }

    public LiveData<UserResponse> getUsers() {
        return usersList;
    }
}





















