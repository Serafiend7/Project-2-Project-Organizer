package com.example.project2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.UserIDRepository;
import com.example.project2.database.entities.UserID;
import com.example.project2.databinding.AdminLandingPageBinding;

import java.util.Locale;

public class AdminLandingPage extends AppCompatActivity {

    AdminLandingPageBinding binding;

    private int loginUserID = -1;
    private UserIDRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = UserIDRepository.getRepository(getApplication());
        setLoginUserID(savedInstanceState);

        UserID user = repository.getUserByUserID(loginUserID);

        binding.LandingPageTextView.setText(String.format(Locale.ENGLISH,"Welcome %s!",user.getUsername()));

        binding.LogoutButton.setOnClickListener(v -> startActivity(new Intent(AdminLandingPage.this, MainActivity.class)));

        binding.ListUsersButton.setOnClickListener(v -> startActivity(new Intent(AdminLandingPage.this, UserListPage.class)));

        binding.AddUserButton.setOnClickListener(v -> startActivity(new Intent(AdminLandingPage.this, AddUserPage.class)));

        binding.DeleteUserButton.setOnClickListener(v -> startActivity(new Intent(AdminLandingPage.this, DeleteUserPage.class)));
    }

    private void setLoginUserID(Bundle savedInstanceState) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(LandingPage.SHARED_PREFERENCE_USERID_KEY, Context.MODE_PRIVATE);

        if (preferences.contains(LandingPage.SHARED_PREFERENCE_USERID_KEY)) {
            loginUserID = preferences.getInt(LandingPage.SHARED_PREFERENCE_USERID_KEY,-1);
        }
        if (loginUserID == -1 & savedInstanceState != null && savedInstanceState.containsKey(LandingPage.SHARED_PREFERENCE_USERID_KEY)) {
            loginUserID = savedInstanceState.getInt(LandingPage.SHARED_PREFERENCE_USERID_KEY,-1);
        }
        if (loginUserID == -1) {
            loginUserID = getIntent().getIntExtra(LandingPage.LANDING_PAGE_USER_ID,-1);
        }
        if (loginUserID == -1) {
            return;
        }

    }
}