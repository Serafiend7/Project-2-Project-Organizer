package com.example.project2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.UserID;
import com.example.project2.Database.entities.UserIDRepository;
import com.example.project2.databinding.AdminLandingPageBinding;

public class AdminLandingPage extends AppCompatActivity {

    AdminLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //todo: binding.LandingPageTextView.setText("Welcome " + username + "!");

        binding.LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLandingPage.this, MainActivity.class));
            }
        });

        binding.ListUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLandingPage.this, UserListPage.class));
            }
        });

        binding.AddUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLandingPage.this, AddUserPage.class));
            }
        });

        binding.DeleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLandingPage.this, DeleteUserPage.class));
            }
        });
    }
}