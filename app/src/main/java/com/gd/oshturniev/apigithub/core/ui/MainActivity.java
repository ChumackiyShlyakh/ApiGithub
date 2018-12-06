package com.gd.oshturniev.apigithub.core.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.databinding.ActivityMainBinding;
import com.gd.oshturniev.apigithub.drawer.viewmodel.DrawerItemsViewModel;
import com.gd.oshturniev.apigithub.login.activity.LoginActivity;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;
import com.gd.oshturniev.apigithub.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<UserResponse> {

    private DrawerItemsViewModel viewModel;
    private Callback<UserResponse> userCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userCallback = this;

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(DrawerItemsViewModel.class);
        binding.setDraweritems(viewModel);

        viewModel.getDrawerItemId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                switch (integer) {
                    case R.id.nav_log_out:
                        if (Utils.isNetworkConnected(getApplicationContext())) {
                            ApiGitHubApplication.getRestClientInstance().getApiGit().delUser().enqueue(userCallback);
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.check_network_connection, Toast.LENGTH_LONG).show();
                        }
                }
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new  GitFragment()).commit();
    }

    @Override
    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        ApiGitHubApplication.getSharedPrefInstance().clearPrefs();
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {
        Toast.makeText(this, R.string.something_is_wrong, Toast.LENGTH_LONG).show();
    }
}
