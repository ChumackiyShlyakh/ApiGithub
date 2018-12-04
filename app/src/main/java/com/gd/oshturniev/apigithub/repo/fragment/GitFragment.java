package com.gd.oshturniev.apigithub.repo.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.repo.viewmodel.UserViewModel;
import com.gd.oshturniev.apigithub.databinding.FragmentGitBinding;
import com.gd.oshturniev.apigithub.utils.Constants;

public class GitFragment extends Fragment {

    private UserResponse user;
    private RecyclerView recyclerView;

    public static GitFragment newInstance(UserResponse user) {
        Bundle args = new Bundle();
        GitFragment gitFragment = new GitFragment();
        args.putParcelable(Constants.USER, user);
        gitFragment.setArguments(args);
        return gitFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentGitBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_git, container, false);

        user = getArguments().getParcelable(Constants.USER);
        UserViewModel userModel = ViewModelProviders.of(this).get(UserViewModel.class);
        View view = binding.getRoot();
        binding.setUser(userModel);

        userModel.getUserResponse().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {

            }
        });
//        recyclerView = (RecyclerView) view.findViewById(R.id.git_list);
        return view;
    }

}
