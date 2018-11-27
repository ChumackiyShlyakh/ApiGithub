package com.gd.oshturniev.apigithub.repo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;

public class UserViewModel extends AndroidViewModel {

    private UserResponse user;

    public final ObservableField<String> login = new ObservableField<>();

    private final LoginModelRequest loginModelRequest = new LoginModelRequest();
    private final MutableLiveData<LoginModelRequest> mutableLiveData = new MutableLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public void getLogin(){
//        loginModelRequest.setPassword(user.getLogin());
        login.set(user.getLogin());
    }

//    mutableLiveData.setValue(loginModelRequest);

    public MutableLiveData<LoginModelRequest> getLoginModelRequest() {
        return mutableLiveData;
    }
}

