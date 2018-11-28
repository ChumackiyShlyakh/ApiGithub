package com.gd.oshturniev.apigithub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.gd.oshturniev.apigithub.login.activity.LoginActivity;

public class StartActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1500;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        image = findViewById(R.id.image);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(StartActivity.this, LoginActivity.class);
                StartActivity.this.startActivity(mainIntent);
                StartActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
