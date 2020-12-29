package com.example.finin.adapters;

import android.view.ViewGroup;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.finin.callBacks.OnItemClickedListener;
import com.example.finin.models.Datum;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProfileRvAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
    OnItemClickedListener mCallback;
    private List<Datum> userList;

    public ProfileRvAdapter(List<Datum> userList, OnItemClickedListener mCallback) {
        this.userList = userList;
        this.mCallback = mCallback;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return UserViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NotNull UserViewHolder holder, int position) {
        Datum userData = userList.get(position);
        ((UserViewHolder) holder).bindTo(userData, mCallback);
    }

    @Override
    public int getItemCount() {
        if (userList == null) {
            return 0;
        }
        return userList.size();
    }

    public void resetData() {
        userList.clear();
        notifyDataSetChanged();
    }

    public void addData(List<Datum> items) {
        if (items != null && userList != null) {
            userList.addAll(items);
            notifyDataSetChanged();
        } else {
            userList.clear();
        }
    }

    public void swapData(List<Datum> items) {
        if (userList != null)
            userList.clear();

        if (items != null && userList != null) {
            userList.addAll(items);
            notifyDataSetChanged();
        }
    }
}