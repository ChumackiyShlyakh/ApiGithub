package com.gd.oshturniev.apigithub.login.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import com.gd.oshturniev.apigithub.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class, LoginViewModel.Patterns.class, Matcher.class, Pattern.class, LoginViewModel.class})
public class LoginViewModelTest {

    LoginViewModel loginViewModel;
    Application application;
    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.mockStatic(LoginViewModel.Patterns.class);
        PowerMockito.mockStatic(Pattern.class);
        PowerMockito.mockStatic(LoginViewModel.class);
        PowerMockito.mock(Matcher.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
        application = mock(Application.class);
        loginViewModel = new LoginViewModel(application);
    }

    @Test
    public void testIfEmailIsNotEmpty() {
        loginViewModel.setEmail("test");
        assertNotNull(loginViewModel.getEmail());
        assertEquals("test", loginViewModel.getEmail());
    }

    @Test
    public void testIfEmailIsInvalid() {
        when(application.getString(eq(R.string.email_error))).thenReturn("Email is invalid!");
        loginViewModel.setEmail("test");
        loginViewModel.getEmailOnFocusChangeListener().onFocusChange(mock(View.class), false);
        assertEquals("Email is invalid!", loginViewModel.errorEmailMessage.get());
    }

    @Test
    public void testIfEmailIsEmpty() {
        when(application.getString(eq(R.string.email_empty))).thenReturn("Email is empty!");
        loginViewModel.setEmail("");
        loginViewModel.getEmailOnFocusChangeListener().onFocusChange(mock(View.class), false);
        assertEquals("Email is empty!", loginViewModel.errorEmailMessage.get());
    }

    @Test
    public void testIfPasswordIsNotEmpty() {
        loginViewModel.setPassword("test");
        assertNotNull(loginViewModel.getPassword());
        assertEquals("test", loginViewModel.getPassword());
    }

    @Test
    public void testIfPasswordIsToSmall() {
        when(application.getString(eq(R.string.password_length_error))).thenReturn("Password is to small!");
        loginViewModel.setPassword("test");
        loginViewModel.getPasswordOnFocusChangeListener().onFocusChange(mock(View.class), false);
        assertEquals("Password is to small!", loginViewModel.errorPasswordMessage.get());
    }

    @Test
    public void testIfPaswordIsEmpty() {
        when(application.getString(eq(R.string.password_empty))).thenReturn("Password is empty!");
        loginViewModel.setPassword("");
        loginViewModel.getPasswordOnFocusChangeListener().onFocusChange(mock(View.class), false);
        assertEquals("Password is empty!", loginViewModel.errorPasswordMessage.get());
    }
}