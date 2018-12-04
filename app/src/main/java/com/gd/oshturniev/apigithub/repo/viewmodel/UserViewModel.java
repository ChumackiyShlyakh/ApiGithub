package com.gd.oshturniev.apigithub.repo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;

public class UserViewModel extends AndroidViewModel implements Observable {

//    private UserResponse user;

//    public final ObservableField<String> login = new ObservableField<>();

//    private final UserResponse userResponse = new UserResponse();
    private final MutableLiveData<UserResponse> mutableLiveData = new MutableLiveData<>();

    @Bindable
    private String login;
//    @Bindable
//    private String password;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
//
//    public String getLogin(){
//        return user.getLogin();
//    }

//    public void getLogin(){
//        login.set(user.getLogin());
//    }

//    mutableLiveData.setValue(loginModelRequest);

    public MutableLiveData<UserResponse> getUserResponse() {
        return mutableLiveData;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}

