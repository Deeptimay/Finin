package com.example.finin.paging;

import com.example.finin.models.Datum;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class UserDataSourceFactory extends DataSource.Factory<Integer, Datum> {

    UserPageKeyedDataSource userPageKeyedDataSource;
    MutableLiveData<UserPageKeyedDataSource> mutableLiveData;

    public UserDataSourceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        userPageKeyedDataSource = new UserPageKeyedDataSource();
        mutableLiveData.postValue(userPageKeyedDataSource);
        return userPageKeyedDataSource;
    }

    public MutableLiveData<UserPageKeyedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
