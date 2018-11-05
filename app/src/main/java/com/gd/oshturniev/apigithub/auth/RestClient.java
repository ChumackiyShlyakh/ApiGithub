package com.gd.oshturniev.apigithub.auth;

import android.util.Base64;

import com.gd.oshturniev.apigithub.TLSSocketFactory;
import com.gd.oshturniev.apigithub.login.activity.LoginFragment;
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

    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;
    private static Retrofit retrofit;
    private static ApiGit apiGit;
    private static String auth;

    public static ApiGit getApiGit() {
        if(apiGit == null) {
            OkHttpClient.Builder builder = null;

//     Define the interceptor, add authentication headers
//    Interceptor interceptor2 = new Interceptor() {
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request newRequest = chain.request().newBuilder().addHeader("Authorization", "Basic " + auth).build();
//            return chain.proceed(newRequest);
//        }
//    };

            ////                    Request.Builder builder = originalRequest.newBuilder().header("Authorization",
////                            Credentials.basic(username, password));
//
//                    Request newRequest = chain.request().newBuilder()
//                            .addHeader("Authorization", "Basic " + encode)
//                            .build();



//            OkHttpClient httpClient = new OkHttpClient.Builder()
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request.Builder builder1 = chain.request().newBuilder();
//                            builder1.addHeader("Accept", "application/json;versions=1");
//                            if (isUserLoggedIn()) {
//                                builder1.addHeader("Authorization", "Basic " + auth);
//                            }
//                            return chain.proceed(builder1.build());
//                        }
//                    }).build();


//            okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
//@Override
//public okhttp3.Response intercept(Chain chain) throws IOException {
//        Request originalRequest = chain.request();
//
//        Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//        Credentials.basic(username, password));
//
//        Request newRequest = builder.build();
//        return chain.proceed(newRequest);
//        }
//        }).sslSocketFactory(new TLSSocketFactory())
//        .build();
//        } catch (KeyManagementException e) {
//        e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//        e.printStackTrace();
//        }
//        }


            {
                try {
                    builder = new OkHttpClient.Builder();
                    builder.sslSocketFactory(new TLSSocketFactory());
//                    builder.interceptors().add(interceptor2);

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
