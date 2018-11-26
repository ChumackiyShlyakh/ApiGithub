package com.gd.oshturniev.apigithub.repo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;

public class UserViewModel extends AndroidViewModel {

    private UserResponse user;

    public final ObservableField<String> login = new ObservableField<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public void setUser(UserResponse user) {
        this.user = user;
        login.set(user.getLogin());
    }
}

