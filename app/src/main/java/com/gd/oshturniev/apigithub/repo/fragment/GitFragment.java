package com.gd.oshturniev.apigithub.repo.fragment;

import android.app.Activity;
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
import android.view.inputmethod.InputMethodManager;

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

//        user = getArguments().getParcelable(Constants.USER);
        UserViewModel userModel = ViewModelProviders.of(this).get(UserViewModel.class);
        View view = binding.getRoot();
        binding.setUser(userModel);

        userModel.getUserResponse().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {

            }
        });

        hideKeyboard(getActivity());
//        recyclerView = (RecyclerView) view.findViewById(R.id.git_list);
        return view;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
