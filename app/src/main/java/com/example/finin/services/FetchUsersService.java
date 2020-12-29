package com.example.finin.services;


import com.example.finin.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FetchUsersService {

    @GET("/api/users")
    Call<UserResponse> getUserList(@Query("page") int pageNo);
}