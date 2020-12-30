package com.example.finin.paging;

import com.example.finin.models.Datum;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class UserDataSourceFactory extends DataSource.Factory<Integer, Datum> {

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    UserPageKeyedDataSource userPageKeyedDataSource;
    MutableLiveData<UserPageKeyedDataSource> mutableLiveData;

    public UserDataSourceFactory(MutableLiveData<Boolean> isLoading) {
        mutableLiveData = new MutableLiveData<>();
        this.isLoading = isLoading;
    }

    @Override
    public DataSource create() {
        userPageKeyedDataSource = new UserPageKeyedDataSource(isLoading);
        mutableLiveData.postValue(userPageKeyedDataSource);
        return userPageKeyedDataSource;
    }

    public MutableLiveData<UserPageKeyedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
