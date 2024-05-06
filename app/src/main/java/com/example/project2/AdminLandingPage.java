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

    private static final int LOGGED_OUT = -1;
    private static final String SHARED_PREFERENCE_USERID_VALUE = "com.example.project2.SHARED_PREFERENCE_USERID_VALUE";
    private static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.example.project2.SAVED_INSTANCE_STATE_USERID_KEY";
    private final String LANDING_PAGE_USER_ID = "com.example.project2.LANDING_PAGE_USER_ID";
    static final String SHARED_PREFERENCE_USERID_KEY = "com.example.project2.SHARED_PREFERENCE_USERID_KEY";
    private static final String ID_EXTRA_KEY = "LandingPage_Received_Id";

    AdminLandingPageBinding binding;

    private int loginUserID = -1;
    private UserIDRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = UserIDRepository.getRepository(getApplication());
        logIn(savedInstanceState);

        if (loginUserID == -1){
            Intent intent = LoginActivity.loginPageActivityIntentFactory(getApplicationContext());
            startActivity(intent);
        }

        assert repository != null;
        UserID user = repository.getUserByUserID(loginUserID);
        String name = user.getUsername();

        binding.LandingPageTextView.setText(String.format(Locale.ENGLISH, "Welcome %s",name));

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

    private void logIn(Bundle savedInstanceState) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY, Context.MODE_PRIVATE);

        if (preferences.contains(SHARED_PREFERENCE_USERID_KEY)) {
            loginUserID = preferences.getInt(SHARED_PREFERENCE_USERID_KEY,LOGGED_OUT);
        }
        if (loginUserID == LOGGED_OUT & savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)) {
            loginUserID = savedInstanceState.getInt(SHARED_PREFERENCE_USERID_KEY,LOGGED_OUT);
        }
        if (loginUserID == LOGGED_OUT) {
            loginUserID = getIntent().getIntExtra(LANDING_PAGE_USER_ID,LOGGED_OUT);
        }
        if (loginUserID == LOGGED_OUT) {
            return;
        }
    }
}