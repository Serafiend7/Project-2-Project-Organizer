package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.ProjectRepository;
import com.example.project2.database.entities.Project;
import com.example.project2.databinding.CreateProjectPageBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateProjectPage extends AppCompatActivity {

    private CreateProjectPageBinding binding;

    private ProjectRepository repository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CreateProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ProjectRepository.getRepository(getApplication());

        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateProjectPage.this, LandingPage.class));
            }
        });

        binding.CreateProjectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createProject();
                Toast.makeText(CreateProjectPage.this, "Project successfully created", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createProject() {

        String name = binding.EnterProjectNameEditTextNumberSigned.getText().toString();

        String creatorName = "user1";
        //        creatorName = binding.;

        ArrayList<String> sharedUserNames = new ArrayList<>();

        String users = binding.EnterSharedUsersEditTextNumberSigned.getText().toString();
        String[] ids = users.split(",");
        sharedUserNames.addAll(Arrays.asList(ids));


        String date = binding.EnterDueDateEditTextNumberSigned.getText().toString();
        String[] v = date.split("/");
        date = v[2] + "-";
        if (Integer.parseInt(v[0]) < 10) {
            date += "0";
        }
        date += v[0] + "-";
        if (Integer.parseInt(v[1]) < 10) {
            date+= "0";
        }
        date += v[1] + " 00:00";
        LocalDateTime dueDate = LocalDateTime.parse(date,formatter);

        Project project = new Project(name,creatorName,sharedUserNames,dueDate);

        repository.insertProject(project);

    }
}
