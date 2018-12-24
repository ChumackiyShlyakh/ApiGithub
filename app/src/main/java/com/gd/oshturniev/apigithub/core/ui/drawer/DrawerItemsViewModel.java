package com.gd.oshturniev.apigithub.core.ui.drawer;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.gd.oshturniev.apigithub.core.arch.SingleLiveEvent;

public class DrawerItemsViewModel extends AndroidViewModel {

    private final SingleLiveEvent<Integer> mutableLiveData = new SingleLiveEvent<>();

    public DrawerItemsViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean onNavigationClick(@NonNull MenuItem item) {
        mutableLiveData.setValue(item.getItemId());
        return true;
    }

    public SingleLiveEvent<Integer> getDrawerItemId() {
        return mutableLiveData;
    }
}
