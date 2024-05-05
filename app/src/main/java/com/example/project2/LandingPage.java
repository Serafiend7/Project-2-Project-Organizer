package com.example.project2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.UserIDRepository;
import com.example.project2.database.entities.UserID;
import com.example.project2.databinding.LandingPageBinding;

import java.util.Locale;

public class LandingPage extends AppCompatActivity {

    private static final int LOGGED_OUT = -1;
    static final String SHARED_PREFERENCE_USERID_VALUE = "com.example.project2.SHARED_PREFERENCE_USERID_VALUE";
    static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.example.project2.SAVED_INSTANCE_STATE_USERID_KEY";
    static final String LANDING_PAGE_USER_ID = "com.example.project2.LANDING_PAGE_USER_ID";
    static final String SHARED_PREFERENCE_USERID_KEY = "com.example.project2.SHARED_PREFERENCE_USERID_KEY";
    static final String ID_EXTRA_KEY = "LandingPage_Received_Id";

    LandingPageBinding binding;

    private int loginUserID = -1;
    private UserIDRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        repository = UserIDRepository.getRepository(getApplication());
        logIn(savedInstanceState);

        if (loginUserID == -1) {
            Intent intent = LoginActivity.loginPageActivityIntentFactory(getApplicationContext());
            startActivity(intent);
        }

        UserID user = repository.getUserByUserID(loginUserID);

        String name = user.getUsername();

        binding.LandingPageTextView.setText(String.format(Locale.ENGLISH, "Welcome %s",name));

        binding.LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
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

    public static Intent landingPageActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, LandingPage.class);
        intent.putExtra(ID_EXTRA_KEY,userId);
        return intent;
    }

    private void logOut() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putInt(SHARED_PREFERENCE_USERID_KEY,LOGGED_OUT);
        preferencesEditor.apply();
        getIntent().putExtra(LANDING_PAGE_USER_ID,LOGGED_OUT);

        startActivity(LoginActivity.loginPageActivityIntentFactory(getApplicationContext()));
    }

    public int getLoginUserID(){
        int loginUserID1 = loginUserID;
        return loginUserID1;
    }

}

