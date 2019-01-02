package com.gd.oshturniev.apigithub.repo.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;

public class DataItemViewModel extends BaseObservable {

    public final ObservableField<String> nameRepo = new ObservableField<>();

    public DataItemViewModel(@NonNull ReposResponse dataModel) {
        nameRepo.set(dataModel.getName());
    }

    public DataItemViewModel(@NonNull FollowingResponse dataModel) {
        nameRepo.set(dataModel.getLogin());
    }
}
