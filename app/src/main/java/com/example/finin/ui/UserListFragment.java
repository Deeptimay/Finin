package com.example.finin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finin.R;
import com.example.finin.adapters.ProfileRvAdapter;
import com.example.finin.callBacks.OnItemClickedListener;
import com.example.finin.models.Datum;
import com.example.finin.viewmodels.UserViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.scopes.FragmentScoped;

@FragmentScoped
public class UserListFragment extends Fragment implements OnItemClickedListener {

    RecyclerView rv_user_list;
    @Inject
    ProfileRvAdapter profileRvAdapter;
    @Inject
    UserViewModel userViewModel;
    @Inject
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        rv_user_list = view.findViewById(R.id.rv_user_list);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }


    private void subscribeObservers() {
        userViewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> movies) {
                profileRvAdapter.submitList(movies);
//                showLoader(false);
            }
        });
    }

    private void initRecyclerView() {
        profileRvAdapter = new ProfileRvAdapter(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(UserListFragment.this.getContext());
        rv_user_list.setLayoutManager(mLayoutManager);
        rv_user_list.setItemAnimator(new DefaultItemAnimator());
        rv_user_list.setAdapter(profileRvAdapter);
    }

    @Override
    public void clickedItem(Bundle data) {
        navController = Navigation.findNavController(UserListFragment.this.getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_userListFragment_to_profileDetailFragment, data);
    }
}