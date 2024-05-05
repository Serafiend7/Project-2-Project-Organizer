package com.example.project2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.ProjectRepository;
import com.example.project2.database.entities.Project;
import com.example.project2.databinding.OpenProjectPageBinding;

public class OpenProjectPage extends AppCompatActivity {

    OpenProjectPageBinding binding;

    ProjectRepository repository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OpenProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ProjectRepository.getRepository(getApplication());
        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OpenProjectPage.this, LandingPage.class));
            }
        });

        binding.OpenProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkProjectID(Integer.parseInt((binding.EnterProjectIdEditTextNumberSigned.getText().toString())))) {
                    Toast.makeText(OpenProjectPage.this, "Incorrect project id", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(OpenProjectPage.this, ProjectPage.class));
                }
            }
        });
    }

    private boolean checkProjectID(int id){

        Project project = repository.getProjectByProjectID(id);

        return id != 0 && project != null;
    }
}
