package com.gd.oshturniev.apigithub.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.repo.adapter.RepoAdapter;

import java.util.List;

public class BindingUtils {

    private BindingUtils() {
    }

    @BindingAdapter("app:errorText")
    public static void errorMessage(TextInputLayout view, String errorMessage) {
        view.setError(errorMessage);
    }

    @BindingAdapter("onFocus")
    public static void bindFocusChange(EditText editText, View.OnFocusChangeListener onFocusChangeListener) {
        if (editText.getOnFocusChangeListener() == null) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelectedListener(
            NavigationView view, NavigationView.OnNavigationItemSelectedListener listener) {
        view.setNavigationItemSelectedListener(listener);
    }

    @BindingAdapter({"app:adapter", "app:data"})
    public void bind(RecyclerView recyclerView, RepoAdapter adapter, List<UserResponse> data) {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }
}
