package com.example.project2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.LandingPageBinding;

public class LandingPage extends AppCompatActivity {

    LandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, MainActivity.class));
            }
        });
    }
    }