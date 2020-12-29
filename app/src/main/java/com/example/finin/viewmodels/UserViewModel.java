package com.example.finin.viewmodels;


import android.app.Application;

import com.example.finin.models.Datum;
import com.example.finin.paging.UserDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class UserViewModel extends AndroidViewModel {

    private final LiveData<PagedList<Datum>> usersList;
    Executor executor;
    UserDataSourceFactory userDataSourceFactory;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userDataSourceFactory = new UserDataSourceFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
//                .setInitialLoadSizeHint(10)
                .setPageSize(6)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        usersList = (new LivePagedListBuilder<Integer, Datum>(userDataSourceFactory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Datum>> getUsers() {
        return usersList;
    }
}





















