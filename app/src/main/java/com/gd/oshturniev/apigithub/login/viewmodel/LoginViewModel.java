package com.gd.oshturniev.apigithub.login.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;

import java.util.Objects;

import static com.gd.oshturniev.apigithub.utils.Constants.EMPTY;

public class LoginViewModel extends AndroidViewModel {

    public final ObservableField<String> errorEmailMessage = new ObservableField<>();
    public final ObservableField<String> errorPasswordMessage = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    private final String LOG_TAG = LoginViewModel.class.getName();

    private View.OnFocusChangeListener onFocusPassword;
    private View.OnFocusChangeListener onFocusEmail;

    private final LoginModelRequest loginModelRequest = new LoginModelRequest();
    private final MutableLiveData<LoginModelRequest> mutableLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
        return onFocusEmail = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (!focused) {
                    setEmail();
                }
            }
        };
    }

    public View.OnFocusChangeListener getPasswordOnFocusChangeListener() {
        return onFocusPassword = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                Log.d(LOG_TAG, "LoginViewModel onFocusChange: " + password.get());
                if (!focused) {
                    Log.d(LOG_TAG, "LoginViewModel !focused: " + password.get());
                    setPassword();
                }
            }
        };
    }

    public void onButtonClick(View view) {
        onFocusPassword.onFocusChange(view, false);
        onFocusEmail.onFocusChange(view, false);
        Log.d(LOG_TAG, "LoginViewModel onButtonClick: " + loginModelRequest.getEmail() + " " + loginModelRequest.getPassword());
        if (!TextUtils.isEmpty(loginModelRequest.getEmail()) && !TextUtils.isEmpty(loginModelRequest.getPassword())) {
            mutableLiveData.setValue(loginModelRequest);
        } else {
            Toast.makeText(getApplication(), getApplication().getString(R.string.checking), Toast.LENGTH_LONG).show();
        }
    }

    public void onPasswordChanged(Editable e) {
        password.set(e.toString());
    }

    public void onEmailChanged(Editable e) {
        email.set(e.toString());
    }

    @NonNull
    private void setPassword() {
        if (!isPasswordEmpty()) {
            errorPasswordMessage.set(getApplication().getString(R.string.empty_password));
        } else {
            if(password.get().length() < 6){
            Log.d(LOG_TAG, "LoginViewModel isPasswordEmpty: " + password.get().length());
                errorPasswordMessage.set(getApplication().getString(R.string.password_length_error));
            } else
            errorPasswordMessage.set(EMPTY);
            loginModelRequest.setPassword(password.get().trim());
        }
    }

    private boolean isPasswordEmpty() {
        return !TextUtils.isEmpty(password.get());
    }

    @NonNull
    private void setEmail() {
        if (isEmailValid()) {
            errorEmailMessage.set(EMPTY);
            loginModelRequest.setEmail(email.get().trim());
            Log.d(LOG_TAG, "LoginViewModel setEmail: " + email.get());
        } else {
            errorEmailMessage.set(!TextUtils.isEmpty(email.get()) ? getApplication().getString(R.string.email_error) :
                    getApplication().getString(R.string.empty_email));
        }
    }

    private boolean isEmailValid() {
        Log.d(LOG_TAG, "LoginViewModel isEmailValid: " + email.get());
        return !TextUtils.isEmpty(email.get()) && Patterns.EMAIL_ADDRESS.matcher(email.get()).matches();
    }

    public MutableLiveData<LoginModelRequest> getLoginModelRequest() {
        return mutableLiveData;
    }
}
