package com.gd.oshturniev.apigithub.core.arch;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

public class BaseAndroidViewModel extends AndroidViewModel implements Observable {

    private transient PropertyChangeRegistry mCallbacks;

    public BaseAndroidViewModel(Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
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
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }

    protected String getString(@StringRes int resourceID) {
        return getApplication().getString(resourceID);
    }

    protected String getString(@StringRes int resId, Object... formatArgs) {
        return getApplication().getString(resId, formatArgs);
    }

    protected int getColor(@ColorRes int resourceID) {
        return ContextCompat.getColor(getApplication(), resourceID);
    }

    protected float getDimen(@DimenRes int resId) {
        return getApplication().getResources().getDimension(resId);
    }

    protected String getQuantityString(@PluralsRes int id, int quantity, Object... formatArgs) {
        return getApplication().getResources().getQuantityString(id, quantity, formatArgs);
    }
}
