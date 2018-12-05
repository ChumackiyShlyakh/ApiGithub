package com.gd.oshturniev.apigithub.drawer.adapter;

import android.databinding.BindingAdapter;
import android.support.design.widget.NavigationView;

public class DataBindingAdapter {
    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            NavigationView view, NavigationView.OnNavigationItemSelectedListener listener) {
        view.setNavigationItemSelectedListener(listener);
    }
}