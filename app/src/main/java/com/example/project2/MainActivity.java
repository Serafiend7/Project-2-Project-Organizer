package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DAC_USERID";
    public static final String PROJECT_TAG = "DAC_PROJECT";
    public static final String ASSIGNMENT_TAG = "DAC_ASSIGNMENT";
    public static final String ANNOUNCEMENT_TAG = "DAC_ANNOUNCEMENT";

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.LoginButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        binding.CreateAccountButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CreateAccountPage.class)));
    }
}