package com.gd.oshturniev.apigithub.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

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
}
