package com.gd.oshturniev.apigithub.login.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.gd.oshturniev.apigithub.BR;
import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;

import static com.gd.oshturniev.apigithub.utils.Constants.EMPTY;

public class LoginViewModel extends AndroidViewModel implements Observable{

    public final ObservableField<String> errorEmailMessage = new ObservableField<>();
    public final ObservableField<String> errorPasswordMessage = new ObservableField<>();

    public final ObservableBoolean isEnabled = new ObservableBoolean(false);

    private View.OnFocusChangeListener onFocusPassword;
    private View.OnFocusChangeListener onFocusEmail;

    private final LoginModelRequest loginModelRequest = new LoginModelRequest();
    private final MutableLiveData<LoginModelRequest> mutableLiveData = new MutableLiveData<>();
    private Observable.OnPropertyChangedCallback onPropertyChangedCallback;
    private PropertyChangeRegistry propertyChangeRegistry;

    @Bindable
    private String email;
    @Bindable
    private String password;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        onPropertyChangedCallback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.email || propertyId == BR.password) {
                    if (isEmailValid() && isPasswordValid()) {
                        isEnabled.set(true);
                    } if (!isEmailValid() || !isPasswordValid()) {
                        isEnabled.set(false);

                    }
                }
            }
        };
        addOnPropertyChangedCallback(onPropertyChangedCallback);
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
                if (!focused) {
                    setPassword();
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
    private void setPassword() {
        if (isPasswordValid()) {
            errorPasswordMessage.set(EMPTY);
            loginModelRequest.setPassword(password.trim());
        } else {
            if (password == null || password.length() < 6) {
                errorPasswordMessage.set(getApplication().getString(R.string.password_length_error));
            }
            if (TextUtils.isEmpty(password)) {
                errorPasswordMessage.set(getApplication().getString(R.string.empty_password));
            }
        }
        notifyPropertyChanged(BR.password);
    }

    private boolean isPasswordValid() {
        return !TextUtils.isEmpty(password) && password.length() > 6;
    }

    @NonNull
    private void setEmail() {
        if (isEmailValid()) {
            errorEmailMessage.set(EMPTY);
            loginModelRequest.setEmail(email.trim());
        } else {
            errorEmailMessage.set(!TextUtils.isEmpty(email) ? getApplication().getString(R.string.email_error) :
                    getApplication().getString(R.string.empty_email));
        }
        notifyPropertyChanged(BR.email);
    }

    private boolean isEmailValid() {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (propertyChangeRegistry == null) {
                propertyChangeRegistry = new PropertyChangeRegistry();
            }
        }
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (propertyChangeRegistry == null) {
                return;
            }
        }
        propertyChangeRegistry.remove(callback);
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (propertyChangeRegistry == null) {
                return;
            }
        }
        propertyChangeRegistry.notifyCallbacks(this, fieldId, null);
    }
}
