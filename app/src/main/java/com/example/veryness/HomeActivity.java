package com.example.veryness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HomeActivity extends AppCompatActivity {
private static int STOP=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },STOP);
        onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

