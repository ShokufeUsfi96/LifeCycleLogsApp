package com.usefi.lifecyclelogsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Log1", "onCreate method.");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log1", "onStart method.");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Log1", "onResume method.");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Log1", "onPause method.");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Log1", "onStop method.");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Log1", "onDestroy method.");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Log1", "onRestart method.");

    }
}
