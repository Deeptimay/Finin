package com.example.finin.adapters;

import android.view.ViewGroup;

import com.example.finin.callBacks.OnItemClickedListener;
import com.example.finin.models.Datum;

import org.jetbrains.annotations.NotNull;

import androidx.paging.PagedListAdapter;

public class ProfileRvAdapter extends PagedListAdapter<Datum, UserViewHolder> {

    OnItemClickedListener mCallback;

    public ProfileRvAdapter(OnItemClickedListener mCallback) {
        super(Datum.USER_COMPARATOR);
        this.mCallback = mCallback;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return UserViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NotNull UserViewHolder holder, int position) {
        Datum userData = getItem(position);
        ((UserViewHolder) holder).bindTo(userData, mCallback);
    }
}