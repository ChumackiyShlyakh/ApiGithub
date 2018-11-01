//package com.gd.oshturniev.apigithub;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.widget.RecyclerView;
//import android.util.Base64;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.io.IOException;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class Fragment_In extends Fragment {
//    final String LOG_TAG = "myLogs";
//
//    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;
//
//    OkHttpClient okHttpClient;
//    private Call<User> callUser;
//    String username = "oshturniev@griddynamics.com";
//    String password = "Sasha502633!";
//    TextView text;
//    TextView text_email;
//    TextView text_password;
//    EditText etEmail;
//    EditText etPassword;
//    Button btn_in;
//
//    Git git = new Git();
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_in, container, false);
//
//        text = (TextView) view.findViewById(R.id.text);
//        text_email = (TextView) view.findViewById(R.id.text_email);
//        text_password = (TextView) view.findViewById(R.id.text_password);
//
//        etEmail = (EditText) view.findViewById(R.id.etEmail);
//        etPassword = (EditText) view.findViewById(R.id.etPassword);
//
//        btn_in = (Button) view.findViewById(R.id.btn_in);
//        btn_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                username = etEmail.getText().toString();
////                password = etPassword.getText().toString();
//                getUserInformation();
//
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.frgmCont, git);
//                ft.commit();
//            }
//        });
//
//        return view;
//    }
//
//
//
//    OkHttpClient.Builder builder;
//    {
//        try {
//            builder = new OkHttpClient.Builder();
//            builder.addInterceptor(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request originalRequest = chain.request();
//
////                    Request.Builder builder = originalRequest.newBuilder().header("Authorization",
////                            Credentials.basic(username, password));
//                    String encode = Base64.encodeToString((username + ":" + password).getBytes(),
//                            Base64.DEFAULT).replace("\n", "");
//
//
//                    Request newRequest = chain.request().newBuilder()
//                            .addHeader("Authorization", "Basic " + encode)
//                            .build();
//
////                    Request newRequest = builder.build();
//                    return chain.proceed(newRequest);
//                }
//            }).sslSocketFactory(new TLSSocketFactory());
//
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(LEVEL_LOG);
//            builder.addInterceptor(interceptor);
//
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://api.github.com")
//            .client(builder.build())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//
//
//    private void getUserInformation() {
//        ApiGit apiGit = retrofit.create(ApiGit.class);
//        callUser = apiGit.getLogin();
//
//        Log.d(LOG_TAG, "getUserInformation: " + " ");
//        callUser.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                User user = response.body();
//                if (user != null) {
//                    text.setText(user.getLogin());
//                    Log.d(LOG_TAG, "if: " + " " + user.getUrl());
//                } else {
//                    Log.d(LOG_TAG, "else: " + " ");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//            }
//        });
//    }
//}
