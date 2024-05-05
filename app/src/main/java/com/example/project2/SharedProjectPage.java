package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.OpenProjectPageBinding;
import com.example.project2.databinding.SharedProjectPageBinding;

public class SharedProjectPage extends AppCompatActivity {

    SharedProjectPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SharedProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SharedProjectPage.this, LandingPage.class));
            }
        });
    }




}
