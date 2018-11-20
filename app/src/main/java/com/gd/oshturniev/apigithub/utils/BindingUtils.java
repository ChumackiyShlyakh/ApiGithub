package com.gd.oshturniev.apigithub.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

//    @BindingAdapter({"app:adapter", "app:data"})
//    public void bind(RecyclerView recyclerView, DataAdapter adapter, List<DataModel> data) {
//        recyclerView.setAdapter(adapter);
//        adapter.updateData(data);
//    }
}
