package com.example.finin.paging;

import com.example.finin.models.Datum;
import com.example.finin.models.UserResponse;
import com.example.finin.services.FetchUsersService;
import com.example.finin.services.RetrofitClient;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPageKeyedDataSource extends PageKeyedDataSource<Integer, Datum> {

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    FetchUsersService fetchUsersService;

    public UserPageKeyedDataSource(MutableLiveData<Boolean> isLoading) {
        this.isLoading = isLoading;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Datum> callback) {

        isLoading.postValue(true);
        fetchUsersService = RetrofitClient.getInstance();
        Call<UserResponse> call = fetchUsersService.getUserList(1, 3);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                List<Datum> userList = response.body().getData();
                callback.onResult(userList, null, 2);
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                isLoading.postValue(false);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Datum> callback) {

        isLoading.postValue(true);
        fetchUsersService = RetrofitClient.getInstance();
        Call<UserResponse> call = fetchUsersService.getUserList(params.key, 3);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                List<Datum> userList = response.body().getData();
                callback.onResult(userList, params.key + 1);
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                isLoading.postValue(false);
            }
        });
    }
}
