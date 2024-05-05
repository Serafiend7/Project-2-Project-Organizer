package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.entities.UserID;
import com.example.project2.database.UserIDRepository;
import com.example.project2.databinding.LoginPageBinding;

public class LoginActivity extends AppCompatActivity {

    LoginPageBinding binding;

    private UserIDRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = UserIDRepository.getRepository(getApplication());

        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkUserID(binding.EnterUsernameEditTextNumberSigned.getText().toString(), binding.EnterPasswordEditTextNumberSigned.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Intent intent = LandingPage.landingPageActivityIntentFactory(getApplicationContext(),repository.getUserByUserName(binding.EnterUsernameEditTextNumberSigned.getText().toString()).getId());
                    startActivity(new Intent(LoginActivity.this, LandingPage.class));
                    if (checkAdminStatus(binding.EnterUsernameEditTextNumberSigned.getText().toString())){
                        startActivity(new Intent(LoginActivity.this, AdminLandingPage.class));
                    }
                }
            }
        });
    }

    private boolean checkUserID(String username, String password){

        UserID userId = repository.getUserByUserName(username);

        if (username != null && userId != null){
            if(password.equals(userId.getPassword())){
                return true;
            }
        }
        return false;
    }

    private boolean checkAdminStatus(String username){
        UserID userId = repository.getUserByUserName(username);
        assert userId != null;
        return userId.isAdmin();
    }
}
