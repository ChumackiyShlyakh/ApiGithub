package com.gd.oshturniev.apigithub.login.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;
import com.gd.oshturniev.apigithub.repo.EmailPassword;

import static com.gd.oshturniev.apigithub.utils.Constants.EMPTY;

public class LoginViewModel extends AndroidViewModel {

    final String LOG_TAG = "myLogs";

    private View.OnFocusChangeListener onFocusEmail;
    private View.OnFocusChangeListener onFocusPassword;

    private final LoginModelRequest loginModelRequest = new LoginModelRequest();
    private final MutableLiveData<LoginModelRequest> mutableLiveData = new MutableLiveData<>();
    public final ObservableField<String> errorEmailMessage = new ObservableField<>();


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
        return onFocusEmail =  new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                Log.d(LOG_TAG, "LoginViewModel getEmailOnFocusChangeListener: " + " " );
                if (et.getText().length() > 0 && !focused) {
                    isEmailValid(et.getText().toString());
                }
            }
        };
    }

    public View.OnFocusChangeListener getPasswordOnFocusChangeListener() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                EditText et = (EditText) view;
                if (et.getText().length() > 0 && !focused) {
                    isPasswordValid(et.getText().toString());
                }
            }
        };
    }

    public void onButtonClick(View view) {
        Log.d(LOG_TAG, "LoginViewModel onButtonClick: " + " " );

        if(!TextUtils.isEmpty(loginModelRequest.getEmail()) || !TextUtils.isEmpty(loginModelRequest.getPassword())) {
            mutableLiveData.setValue(loginModelRequest);
            new EmailPassword().saveLoginDetails(loginModelRequest.getEmail(), loginModelRequest.getPassword());
        } else {
            Toast.makeText(getApplication(), "Check your creds!", Toast.LENGTH_LONG).show();
        }
    }

    @BindingAdapter("onFocus")
    public static void bindFocusChange(EditText editText, View.OnFocusChangeListener onFocusChangeListener) {
        if (editText.getOnFocusChangeListener() == null) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }

    private boolean isEmailValid(String email) {
        // Minimum a@b.c
        if (email != null && email.length() > 5) {
            int indexOfAt = email.indexOf("@");
            int indexOfDot = email.lastIndexOf(".");
            if (indexOfAt > 0 && indexOfDot > indexOfAt && indexOfDot < email.length() - 1) {
                errorEmailMessage.set(EMPTY);
                loginModelRequest.setEmail(email.trim());
                return true;
            } else {
                errorEmailMessage.set("Enter a valid email address");
                Toast.makeText(getApplication(), "Email is wrong!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return false;
    }

    private boolean isPasswordValid(String password) {
        if (password != null && password.length() > 5) {
            loginModelRequest.setPassword(password.trim());
            return true;
        } else {
//            errorMessage.set("Password Length should be greater than 5");
            Toast.makeText(getApplication(), "Password is wrong!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public MutableLiveData<LoginModelRequest> getLoginModelRequest() {
        return mutableLiveData;
    }
}
