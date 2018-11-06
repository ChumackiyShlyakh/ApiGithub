package com.gd.oshturniev.apigithub.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

public class BindingUtils {

    private BindingUtils() {
    }

    @BindingAdapter("app:errorText")
    public static void errorMessage(TextInputLayout view, String errorMessage) {
        view.setError(errorMessage);
    }
}
