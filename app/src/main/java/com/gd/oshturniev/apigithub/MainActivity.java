package com.gd.oshturniev.apigithub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.gd.oshturniev.apigithub.auth.Api;
import com.gd.oshturniev.apigithub.auth.ApiConstants;
import com.gd.oshturniev.apigithub.auth.ApiGit;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity // FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, Callback<User> {

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
    String encode;

    GitFragment gitFragment;

//    android.app.FragmentTransaction fTrans;
    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_main, container, false);
//
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
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
//            }
//        });
//
//
//        DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        Log.d(LOG_TAG, "onCreate: " + " ");
//
//
//        return view;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gitFragment = new GitFragment();

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
//                getUserInformation();

                text_email.setVisibility(View.GONE);
                text_password.setVisibility(View.GONE);
                etEmail.setVisibility(View.GONE);
                etPassword.setVisibility(View.GONE);
                btn_in.setVisibility(View.GONE);

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frgmCont, gitFragment);
                ft.commit();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Log.d(LOG_TAG, "onCreate: " + " ");

        encode = Base64.encodeToString((username + ":" + password).getBytes(),
                Base64.DEFAULT).replace("\n", "");

        Call<User> call = Api.getApiGit().getUser("Basic " + encode);
        call.enqueue(this);
    }

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

    private void getUserInformation() {
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
    }

    @Override
    public void onBackPressed() {

//        fTrans = getFragmentManager().beginTransaction();
//        fTrans.replace(R.id.frgmCont, continents).commit();

        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment : fm.getFragments()) {
            if (fragment instanceof OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }
        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            Log.d(LOG_TAG, "onBackPressed_4 ");
            super.onBackPressed();
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
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
//        fTrans = getFragmentManager().beginTransaction();
        Fragment fragment = null;
        Class fragmentClass = null;

        int id = item.getItemId();

        if (id == R.id.nav_followers) {
//            fTrans.replace(R.id.frgmCont, countryCityFlagFragment).commit();
            fragmentClass = GitFragment.class;

        } else if (id == R.id.nav_following) {

        } else if (id == R.id.nav_load) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_save) {

        } else if (id == R.id.nav_exit) {

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frgmCont, fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}