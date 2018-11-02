package com.gd.oshturniev.apigithub.auth;

import com.gd.oshturniev.apigithub.TLSSocketFactory;
import com.gd.oshturniev.apigithub.utils.ApiConstants;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;
    private static Retrofit retrofit;
    private static ApiGit apiGit;

    public static ApiGit getApiGit() {
        if(apiGit == null) {
            OkHttpClient.Builder builder = null;
            {
                try {
                    builder = new OkHttpClient.Builder();
                    builder.sslSocketFactory(new TLSSocketFactory());

                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(LEVEL_LOG);
                    builder.addInterceptor(interceptor);

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
