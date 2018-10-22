package com.youngtard.tacsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_launch_screen);
        Intent i = new Intent(LaunchScreenActivity.this, SignUpEndUser.class);
        startActivity(i);
        finish();

    }
}
