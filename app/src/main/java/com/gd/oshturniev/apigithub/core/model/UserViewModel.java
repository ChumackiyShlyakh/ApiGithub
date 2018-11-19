package com.gd.oshturniev.apigithub.core.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.response.User;

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
}

