package com.parveendala.dagger.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.parveendala.dagger.R;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
