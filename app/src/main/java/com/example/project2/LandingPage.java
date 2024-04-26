package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project2.databinding.LandingPageBinding;
import com.example.project2.databinding.LoginPageBinding;

public class LandingPage extends AppCompatActivity {

    LandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}