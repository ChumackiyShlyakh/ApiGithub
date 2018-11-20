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

    private final String LOG_TAG = LoginViewModel.class.getName();

    private View.OnFocusChangeListener onFocusPassword;
    private View.OnFocusChangeListener onFocusEmail;

    private final LoginModelRequest loginModelRequest = new LoginModelRequest();
    private final MutableLiveData<LoginModelRequest> mutableLiveData = new MutableLiveData<>();
    public final ObservableField<String> errorEmailMessage = new ObservableField<>();
    public final ObservableField<String> errorPasswordMessage = new ObservableField<>();

    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
            Log.d(LOG_TAG, "LoginViewModel getEmailOnFocusChangeListener: " + password.get());
        return onFocusEmail = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                Log.d(LOG_TAG, "LoginViewModel onFocusChange: " + password.get());
                if (!focused) {
                    Log.d(LOG_TAG, "LoginViewModel !focused: " + password.get());
                    setEmail();
                }
            }
        };
    }

    public View.OnFocusChangeListener getPasswordOnFocusChangeListener() {
        return onFocusPassword = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (!focused) {
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

    @SuppressLint("NewApi")
    private void setPassword() {
        if (isPasswordValid()) {
            errorPasswordMessage.set(EMPTY);
            loginModelRequest.setPassword(Objects.requireNonNull(password.get()).trim());
            Log.d(LOG_TAG, "LoginViewModel setPassword: " + password.get());
        } else {
            errorPasswordMessage.set(!TextUtils.isEmpty(password.get()) ? getApplication().getString(R.string.password_error) :
                    getApplication().getString(R.string.empty_password));
        }
    }

    private boolean isPasswordValid() {
        return !TextUtils.isEmpty(password.get());
    }

    @SuppressLint("NewApi")
    private void setEmail() {
        if (isEmailValid()) {
            errorEmailMessage.set(EMPTY);
            loginModelRequest.setEmail(Objects.requireNonNull(email.get()).trim());
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
