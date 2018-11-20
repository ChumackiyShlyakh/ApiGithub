package com.gd.oshturniev.apigithub.login.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.User;
import com.gd.oshturniev.apigithub.core.model.UserViewModel;
import com.gd.oshturniev.apigithub.databinding.FragmentGitBinding;
import com.gd.oshturniev.apigithub.utils.Constants;

public class GitFragment extends Fragment {

    private User user;

    private RecyclerView recyclerView;

    public static GitFragment newInstance(User user){
        Bundle args = new Bundle();
        GitFragment gitFragment = new GitFragment();
        args.putParcelable(Constants.USER, user);
        gitFragment.setArguments(args);

        return gitFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null && getArguments().getParcelable(Constants.USER) != null)
        user = getArguments().getParcelable(Constants.USER);
    }

    public int getShownIndex() {
        return getArguments().getInt(Constants.USER, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentGitBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_git, container, false);
        UserViewModel userModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        View view = binding.getRoot();
        binding.setUser(userModel);
        getShownIndex();

        recyclerView = (RecyclerView) view.findViewById(R.id.git_list);
        return view;
    }
}
