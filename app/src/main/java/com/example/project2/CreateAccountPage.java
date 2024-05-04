package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.entities.UserID;
import com.example.project2.database.UserIDRepository;
import com.example.project2.databinding.CreateAccountPageBinding;

public class CreateAccountPage extends AppCompatActivity {

    CreateAccountPageBinding binding;

    private UserIDRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CreateAccountPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = UserIDRepository.getRepository(getApplication());

        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateAccountPage.this, MainActivity.class));
            }
        });

        binding.CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.EnterPasswordEditTextNumberSigned.getText().toString().equals(binding.ConfirmPasswordEditTextNumberSigned.getText().toString())){
                    Toast.makeText(CreateAccountPage.this, "Passwords do not match, please re-enter your password", Toast.LENGTH_SHORT).show();
                }
                else {
                    createAccount(binding.EnterUsernameEditTextNumberSigned.getText().toString(), binding.EnterPasswordEditTextNumberSigned.getText().toString(), binding.ConfirmPasswordEditTextNumberSigned.getText().toString());
                    Toast.makeText(CreateAccountPage.this, "Account successfully created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createAccount(String username, String password, String confirmPassword){
        UserID userId = new UserID(username, password, false);
        UserID currentUser = repository.getUserByUserName(username);
        if (currentUser != null){
            userId.setId(currentUser.getId());
        }
        repository.insertUserID(userId);
    }
}