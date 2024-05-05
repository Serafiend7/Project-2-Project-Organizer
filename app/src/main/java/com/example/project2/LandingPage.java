package com.example.project2;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.UserIDRepository;
import com.example.project2.databinding.LandingPageBinding;

import java.util.Locale;

public class LandingPage extends AppCompatActivity {

    private static final String ID_EXTRA_KEY = "LandingPage_Received_Id";

    LandingPageBinding binding;

//    private UserIDRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        repository = UserIDRepository.getRepository(getApplication());
//
//        int id = getIntent().getIntExtra(ID_EXTRA_KEY,1);
//        String name = repository.getUserByUserID(id).getUsername();
//        binding.LandingPageTextView.setText(String.format(Locale.ENGLISH, "Welcome %.2",name));

        binding.LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, MainActivity.class));
            }
        });

        binding.CreateProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, CreateProjectPage.class));
            }
        });

        binding.OpenProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, OpenProjectPage.class));
            }
        });

        binding.SharedProjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, SharedProjectPage.class));
            }
        });
    }

//    public static Intent landingPageActivityIntentFactory(Context context, int userId) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        intent.putExtra(ID_EXTRA_KEY,userId);
//        return intent;
//    }
    }