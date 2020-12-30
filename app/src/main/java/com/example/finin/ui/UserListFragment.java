package com.example.finin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finin.R;
import com.example.finin.adapters.ProfileRvAdapter;
import com.example.finin.callBacks.OnItemClickedListener;
import com.example.finin.databinding.UserListFragmentBinding;
import com.example.finin.models.Datum;
import com.example.finin.viewmodels.UserViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dagger.hilt.android.scopes.FragmentScoped;

@FragmentScoped
public class UserListFragment extends Fragment implements OnItemClickedListener {

    @Inject
    ProfileRvAdapter profileRvAdapter;
    @Inject
    UserViewModel userViewModel;
    @Inject
    NavController navController;
    UserListFragmentBinding userListFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.user_list_fragment, container, false);
        return userListFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        initView(view);
    }

    private void initView(View view) {
        userListFragmentBinding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userListFragmentBinding.swipeLayout.setRefreshing(false);
                userViewModel.refresh();
            }
        });

        userListFragmentBinding.swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers() {
        userViewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isLoading) {
                if (isLoading != null) {
                    if (isLoading) {
                        userListFragmentBinding.lavLoader.playAnimation();
                        userListFragmentBinding.lavLoader.setVisibility(View.VISIBLE);
                    } else {
                        userListFragmentBinding.lavLoader.pauseAnimation();
                        userListFragmentBinding.lavLoader.setVisibility(View.GONE);
                    }
                }
            }
        });
        userViewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> movies) {
                profileRvAdapter.submitList(movies);
            }
        });
    }


    private void initRecyclerView() {
        profileRvAdapter = new ProfileRvAdapter(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(UserListFragment.this.getContext());
        userListFragmentBinding.rvUserList.setLayoutManager(mLayoutManager);
        userListFragmentBinding.rvUserList.setItemAnimator(new DefaultItemAnimator());
        userListFragmentBinding.rvUserList.setAdapter(profileRvAdapter);
    }


    @Override
    public void clickedItem(Bundle data) {
        navController = Navigation.findNavController(UserListFragment.this.getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_userListFragment_to_profileDetailFragment, data);
    }
}