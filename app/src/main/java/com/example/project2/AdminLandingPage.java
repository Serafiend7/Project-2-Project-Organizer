package com.example.project2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.AdminLandingPageBinding;

public class AdminLandingPage extends AppCompatActivity {

    AdminLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLandingPage.this, MainActivity.class));
            }
        });
    }
}