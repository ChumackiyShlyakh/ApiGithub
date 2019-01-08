package com.gd.oshturniev.apigithub.login.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.gd.oshturniev.apigithub.BR;
import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.arch.BaseAndroidViewModel;
import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;

import java.util.regex.Pattern;

import javax.inject.Inject;

import static com.gd.oshturniev.apigithub.utils.Constants.EMPTY;

public class LoginViewModel extends BaseAndroidViewModel {

    public final ObservableField<String> errorEmailMessage = new ObservableField<>();
    public final ObservableField<String> errorPasswordMessage = new ObservableField<>();
    public final ObservableBoolean isEnabled = new ObservableBoolean(false);

    private final int PASSWORD_MIN_LENGTH = 6;

    private final LoginModelRequest loginModelRequest = new LoginModelRequest();
    private final MutableLiveData<LoginModelRequest> mutableLiveData = new MutableLiveData<>();
    private Observable.OnPropertyChangedCallback onPropertyChangedCallback;

    @Bindable
    private String email;
    @Bindable
    private String password;

    @Inject
    public LoginViewModel(@NonNull Application application) {
        super(application);
        onPropertyChangedCallback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.email || propertyId == BR.password) {
                    if (isEmailValid() && isPasswordValid()) {
                        isEnabled.set(true);
                    }
                    if (!isEmailValid() || !isPasswordValid()) {
                        isEnabled.set(false);
                    }
                }
            }
        };
        addOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    public void onEmailChanged(Editable e) {
        setEmailIsValid();
        notifyPropertyChanged(BR.email);
    }

    public void onPasswordChanged(Editable e) {
        setPasswordIsValid();
        notifyPropertyChanged(BR.password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
        return new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (!focused) {
                    setEmailIsValid();
                }
            }
        };
    }

    public View.OnFocusChangeListener getPasswordOnFocusChangeListener() {
        return new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (!focused) {
                    setPasswordIsValid();
                }
            }
        };
    }

    public void onButtonClick(View view) {
        mutableLiveData.setValue(loginModelRequest);
    }

    public MutableLiveData<LoginModelRequest> getLoginModelRequest() {
        return mutableLiveData;
    }

    @NonNull
    private void setPasswordIsValid() {
        if (isPasswordValid()) {
            errorPasswordMessage.set(EMPTY);
            loginModelRequest.setPassword(password.trim());
        } else {
            if (TextUtils.isEmpty(password)) {
                errorPasswordMessage.set(getString(R.string.password_empty));
            } else if (password.length() < PASSWORD_MIN_LENGTH) {
                errorPasswordMessage.set(getString(R.string.password_length_error));
            }
        }
        notifyPropertyChanged(BR.password);
    }

    private boolean isPasswordValid() {
        return !TextUtils.isEmpty(password) && password.length() >= PASSWORD_MIN_LENGTH;
    }

    @NonNull
    private void setEmailIsValid() {
        if (isEmailValid()) {
            errorEmailMessage.set(EMPTY);
            loginModelRequest.setEmail(email.trim());
        } else {
            errorEmailMessage.set(!TextUtils.isEmpty(email) ? getString(R.string.email_error) :
                    getString(R.string.email_empty));
        }
        notifyPropertyChanged(BR.email);
    }

    private boolean isEmailValid() {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    protected static class Patterns {

        public static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        public static final Pattern EMAIL_ADDRESS = Pattern.compile(EMAIL_PATTERN);
    }
}
