package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.UserID;
import com.example.project2.Database.entities.UserIDRepository;
import com.example.project2.databinding.UserListPageBinding;

import java.util.ArrayList;

public class UserListPage extends AppCompatActivity {
    UserListPageBinding binding;
    private UserIDRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserListPageBinding.inflate(getLayoutInflater());

        repository = UserIDRepository.getRepository(getApplication());

        setContentView(binding.getRoot());
        binding.ListOfUsers.setText(listUsers());
        binding.ListOfUsers.setMovementMethod(new ScrollingMovementMethod());

        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserListPage.this, AdminLandingPage.class));
            }
        });
    }

    private String listUsers(){
        ArrayList<UserID> listOfUsers = repository.getAllIDs();
        //listOfUsers.forEach(x -> x.toString());
        String string = "";

        for (UserID user : listOfUsers){
            string += user.toString() + "\n\n";
        }

        return string;
    }
}