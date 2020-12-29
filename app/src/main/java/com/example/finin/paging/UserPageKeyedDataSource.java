package com.example.finin.paging;

import com.example.finin.models.Datum;
import com.example.finin.models.UserResponse;
import com.example.finin.services.FetchUsersService;
import com.example.finin.services.RetrofitClient;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPageKeyedDataSource extends PageKeyedDataSource<Integer, Datum> {

    FetchUsersService fetchUsersService;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Datum> callback) {

        fetchUsersService = RetrofitClient.getInstance();
        Call<UserResponse> call = fetchUsersService.getUserList(1);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                List<Datum> userList = response.body().getData();
                callback.onResult(userList, null, 2);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Datum> callback) {

        fetchUsersService = RetrofitClient.getInstance();
        Call<UserResponse> call = fetchUsersService.getUserList(params.key);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                List<Datum> userList = response.body().getData();
                callback.onResult(userList, params.key + 1);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }
}
