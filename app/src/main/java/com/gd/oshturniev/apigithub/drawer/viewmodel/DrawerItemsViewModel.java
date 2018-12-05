package com.gd.oshturniev.apigithub.drawer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Observable;
import android.support.annotation.NonNull;
import android.view.MenuItem;

public class DrawerItemsViewModel extends AndroidViewModel implements Observable {

    private final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

    public DrawerItemsViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean onNavigationClick(@NonNull MenuItem item) {

        mutableLiveData.setValue(item.getItemId());
        return true;
    }

    public MutableLiveData<Integer> getLoginModelRequest() {
        return mutableLiveData;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
