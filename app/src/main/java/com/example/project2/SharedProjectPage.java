package com.example.project2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.SharedProjectPageBinding;

public class SharedProjectPage extends AppCompatActivity {

    SharedProjectPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SharedProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.PreviousPageButton.setOnClickListener(v -> startActivity(new Intent(SharedProjectPage.this, LandingPage.class)));
    }




}
