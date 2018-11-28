package com.gd.oshturniev.apigithub.repo.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.login.fragment.LoginFragment;
import com.gd.oshturniev.apigithub.login.viewmodel.LoginViewModel;
import com.gd.oshturniev.apigithub.repo.viewmodel.UserViewModel;
import com.gd.oshturniev.apigithub.databinding.FragmentGitBinding;
import com.gd.oshturniev.apigithub.utils.Constants;

public class GitFragment extends Fragment {

    private static final String LOG_TAG = "myLogs";
    private UserResponse user;
    private RecyclerView recyclerView;
    TextView textView3;
    Button btn_exit;

    public static GitFragment newInstance(UserResponse user) {
        Bundle args = new Bundle();
        GitFragment gitFragment = new GitFragment();
        args.putParcelable(Constants.USER, user);
        gitFragment.setArguments(args);
        Log.d(LOG_TAG, "GitFragment newInstance: " + " " + ApiGitHubApplication.getSharedPrefInstance().getEmail());
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

        UserViewModel userModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        View view = binding.getRoot();

        binding.setUser(userModel);
        textView3 = view.findViewById(R.id.textView3);
        btn_exit = view.findViewById(R.id.btn_exit);

        user = getArguments().getParcelable(Constants.USER);

        textView3.setText(user.getLogin());

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiGitHubApplication.getSharedPrefInstance().saveLoginDetails(null, null);
//            Intent mainIntent = new Intent(StartActivity.this, LoginActivity.class);
//            StartActivity.this.startActivity(mainIntent);
                FragmentManager fragmentManager = getFragmentManager();
                try {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment.class.newInstance()).commit();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
//        recyclerView = (RecyclerView) view.findViewById(R.id.git_list);
        return view;
    }
}
