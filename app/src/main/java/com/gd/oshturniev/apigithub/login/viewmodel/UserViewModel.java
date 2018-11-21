package com.gd.oshturniev.apigithub.login.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.RecyclerAdapter;
import com.gd.oshturniev.apigithub.core.model.response.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {

    private User user;

    public final ObservableField<String> login = new ObservableField<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public void setUser(User user) {
        this.user = user;
        login.set(user.getLogin());
    }

//    private RecyclerAdapter adapter = new RecyclerAdapter(R.layout.view_dog_breed, this);;
}

