package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.UserID;
import com.example.project2.Database.entities.UserIDRepository;
import com.example.project2.databinding.AddUserPageBinding;

import java.util.Locale;

public class AddUserPage extends AppCompatActivity {

    AddUserPageBinding binding;
    private UserIDRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddUserPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = UserIDRepository.getRepository(getApplication());

        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddUserPage.this, AdminLandingPage.class));
            }
        });

        binding.CreateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.EnterAdminStatusEditTextNumberSigned.getText().toString().toLowerCase(Locale.ROOT).equals("true") && !binding.EnterAdminStatusEditTextNumberSigned.getText().toString().toLowerCase(Locale.ROOT).equals("false")){
                    Toast.makeText(AddUserPage.this, "Incorrect admin status, please re-enter admin status as either true or false.", Toast.LENGTH_SHORT).show();
                }
                else {
                    addUser(binding.EnterUsernameEditTextNumberSigned.getText().toString(), binding.EnterPasswordEditTextNumberSigned.getText().toString(), Boolean.parseBoolean(binding.EnterAdminStatusEditTextNumberSigned.getText().toString().toLowerCase(Locale.ROOT)));
                    Toast.makeText(AddUserPage.this, "User successfully created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addUser(String username, String password, Boolean adminStatus){
        UserID userId = new UserID(username, password, adminStatus);
        UserID currentUser = repository.getUserByUserName(username);
        if (currentUser != null){
            userId.setId(currentUser.getId());
        }
        repository.insertUserID(userId);
    }
}