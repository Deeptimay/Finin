package com.example.finin.repositories;


import android.util.Log;

import com.example.finin.models.UserResponse;
import com.example.finin.services.RetrofitClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static final String TAG = "UserRepository";
    private static UserRepository instance;
    MutableLiveData<UserResponse> listMutableLiveData = new MutableLiveData<>();

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public LiveData<UserResponse> loadUsers(int pageNo) {
        Call<UserResponse> call = RetrofitClient.getInstance().getUserList(pageNo);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.raw().cacheResponse() != null)
                    Log.e("Network", "response came from cache");

                if (response.raw().networkResponse() != null)
                    Log.e("Network", "response came from server");

                if (response.body() != null)
                    listMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("UserRepository", "Error in fetching user", t);
            }
        });
        return listMutableLiveData;
    }
}












