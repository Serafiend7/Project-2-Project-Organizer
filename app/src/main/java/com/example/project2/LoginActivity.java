package com.example.project2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.ActivityMainBinding;
import com.example.project2.databinding.LoginPageBinding;

public class LoginActivity extends AppCompatActivity {


    LoginPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
