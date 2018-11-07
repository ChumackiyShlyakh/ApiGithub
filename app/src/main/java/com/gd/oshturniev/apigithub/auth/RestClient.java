package com.gd.oshturniev.apigithub.auth;

import android.util.Base64;
import android.util.Log;

import com.gd.oshturniev.apigithub.TLSSocketFactory;
import com.gd.oshturniev.apigithub.login.activity.LoginFragment;
import com.gd.oshturniev.apigithub.repo.EmailPassword;
import com.gd.oshturniev.apigithub.utils.ApiConstants;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    static final String LOG_TAG = "myLogs";
    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;
    public static Retrofit retrofit;
    private static ApiGit apiGit;

    public static ApiGit getApiGit() {
        if(apiGit == null) {
            OkHttpClient.Builder builder = null;

            {
                try {
                    builder = new OkHttpClient.Builder();
                    builder.sslSocketFactory(new TLSSocketFactory());

                    Interceptor authInterceptor = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();

                            Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                                    Credentials.basic(EmailPassword.getEmail(), EmailPassword.getPassword()));

                            Log.d(LOG_TAG, "RestClient ApiGit: " + " " + EmailPassword.getEmail() +
                                    " " + EmailPassword.getPassword());

                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    };

                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(LEVEL_LOG);
                    builder.addInterceptor(interceptor);
                    builder.addInterceptor(authInterceptor);

                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
                    retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_GITHUB_URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiGit = retrofit.create(ApiGit.class);
        }
        return apiGit;
    }
}
