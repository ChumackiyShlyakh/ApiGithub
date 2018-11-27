package com.gd.oshturniev.apigithub.repo.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.login.viewmodel.LoginViewModel;
import com.gd.oshturniev.apigithub.repo.viewmodel.UserViewModel;
import com.gd.oshturniev.apigithub.databinding.FragmentGitBinding;
import com.gd.oshturniev.apigithub.utils.Constants;

public class GitFragment extends Fragment {

    private static final String LOG_TAG = "myLogs";
    private UserResponse user;
    private RecyclerView recyclerView;
    TextView textView3;

    public static GitFragment newInstance(UserResponse user){
        Bundle args = new Bundle();
        GitFragment gitFragment = new GitFragment();
        args.putParcelable(Constants.USER, user);
        gitFragment.setArguments(args);
        Log.d(LOG_TAG, "GitFragment newInstance: " + " " );
        return gitFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(getArguments() != null && getArguments().getParcelable(Constants.USER) != null)
//        user = getArguments().getParcelable(Constants.USER);
    }

//    public int getShownIndex() {
//        return getArguments().getInt(Constants.USER, 0);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentGitBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_git, container, false);

        UserViewModel userModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        View view = binding.getRoot();
        binding.setUser(userModel);
//        getShownIndex();
        textView3 = (TextView) view.findViewById(R.id.textView3);
//        Bundle bundle = this.getArguments();
//        Log.d(LOG_TAG, "GitFragment onCreateView: " + " " + bundle);
//        if (bundle != null) {
            user = getArguments().getParcelable(Constants.USER);

        textView3.setText(user.getLogin());

//            user = bundle.getParcelable(Constants.USER);
            Log.d(LOG_TAG, "GitFragment onCreateView 2: " + " " + user);
//            userModel.getLogin();
//        }

        recyclerView = (RecyclerView) view.findViewById(R.id.git_list);
        return view;
    }
}
