package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.UserID;
import com.example.project2.Database.entities.UserIDRepository;
import com.example.project2.databinding.DeleteUserPageBinding;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeleteUserPage extends AppCompatActivity {

    DeleteUserPageBinding binding;
    private UserIDRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DeleteUserPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = UserIDRepository.getRepository(getApplication());

        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteUserPage.this, AdminLandingPage.class));
            }
        });

        binding.DeleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkUserStatus(Integer.parseInt(binding.EnterUserIDEditTextNumberSigned.getText().toString()))){
                    Toast.makeText(DeleteUserPage.this, "This user does not exist", Toast.LENGTH_SHORT).show();
                }
                else {
                    deleteUser(Integer.parseInt(binding.EnterUserIDEditTextNumberSigned.getText().toString()));
                    Toast.makeText(DeleteUserPage.this, "User successfully deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkUserStatus(Integer id){

        ArrayList<UserID> listOfUsers = repository.getAllIDs();

        for (UserID userid : listOfUsers){
            if (userid.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void deleteUser(Integer id){
        repository.delete(repository.getUserByUserID(id));
    }
}