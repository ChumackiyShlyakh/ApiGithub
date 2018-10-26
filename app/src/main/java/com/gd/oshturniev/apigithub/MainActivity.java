package com.gd.oshturniev.apigithub;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final String LOG_TAG = "myLogs";



    OkHttpClient okHttpClient;
    private Call<User> callUser;
    String username = "oshturniev@griddynamics.com";
    String password = "Sasha502633!";
    TextView text;
    TextView text_email;
    TextView text_password;
    EditText etEmail;
    EditText etPassword;
    Button btn_in;


    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView) findViewById(R.id.text);
        text_email = (TextView) findViewById(R.id.text_email);
        text_password = (TextView) findViewById(R.id.text_password);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btn_in = (Button) findViewById(R.id.btn_in);
        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                username = etEmail.getText().toString();
//                password = etPassword.getText().toString();
                getUserInformation();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Log.d(LOG_TAG, "onCreate: " + " ");

    }

    OkHttpClient.Builder builder;
    {
        try {
            builder = new OkHttpClient.Builder();
            builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();

//                    Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//                            Credentials.basic(username, password));
                    String encode = Base64.encodeToString((username + ":" + password).getBytes(),
                            Base64.DEFAULT).replace("\n", "");


                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Basic " + encode)
                            .build();

//                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                }
            }).sslSocketFactory(new TLSSocketFactory());

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(LEVEL_LOG);
            builder.addInterceptor(interceptor);

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private void getUserInformation() {
        ApiGit apiGit = retrofit.create(ApiGit.class);
        callUser = apiGit.getLogin();

        Log.d(LOG_TAG, "getUserInformation: " + " ");
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null) {
                    text.setText(user.getLogin());
                    Log.d(LOG_TAG, "if: " + " " + user.getUrl());
                } else {
                    Log.d(LOG_TAG, "else: " + " ");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }


//
//
//
//
//    ApiGit apiGit = retrofit.create(ApiGit.class);
//    Call<User> api = apiGit.getUser();
//api.enqueue(new Callback<User>() {
//        @Override
//        public void onResponse(Call<User> call, Response<User> response) {
//            if (response.isSuccessful()) {
//                log("response " + response.body().size());
//            } else {
//                log("response code " + response.code());
//            }
//        }
//
//        @Override
//        public void onFailure(Call<User> call, Throwable t) {
//            log("failure " + t);
//        }
//    });


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


// {
//         try {
//         okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
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
//
//        Retrofit retrofit = new Retrofit.Builder()
//        .baseUrl("https://api.github.com")
//        .client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
//        .build();