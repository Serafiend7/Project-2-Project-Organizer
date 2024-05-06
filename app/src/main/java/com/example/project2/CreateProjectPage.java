package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.ProjectRepository;
import com.example.project2.database.UserIDRepository;
import com.example.project2.database.entities.Project;
import com.example.project2.database.entities.UserID;
import com.example.project2.databinding.CreateProjectPageBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateProjectPage extends AppCompatActivity {


    private static final String ID_EXTRA_KEY = "CreateProjectPage_Received_Id";

    private CreateProjectPageBinding binding;

    private ProjectRepository repository;
    private UserIDRepository userRepository;

    private int creatorID;

    private int projectID;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CreateProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateProjectPage.this, LandingPage.class));
            }
        });

        binding.CreateProjectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                repository = ProjectRepository.getRepository(getApplication());
                userRepository = UserIDRepository.getRepository(getApplication());
                getCreatorID(savedInstanceState);
                createProject();
                toastTextId(projectID);
            }
        });
    }

    private void createProject() {

        String name = binding.EnterProjectNameEditTextNumberSigned.getText().toString();

        UserID user = userRepository.getUserByUserID(creatorID);
        String creatorName = user.getUsername();

        ArrayList<Integer> sharedUserIDs = new ArrayList<>();

        String users = binding.EnterSharedUsersEditTextNumberSigned.getText().toString();
        String[] names = users.split(",");

        for (String s : names) {
            sharedUserIDs.add(userRepository.getUserByUserName(s).getId());
        }

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

        Project project = new Project(name,creatorName,sharedUserIDs,dueDate);

        repository.insertProject(project);
        projectID = repository.getProjectByProjectName(project.getName()).getId();

    }

    public static Intent createProjectPageIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, CreateProjectPage.class);
        intent.putExtra(ID_EXTRA_KEY,userId);
        return intent;
    }

    private void getCreatorID(Bundle savedInstanceState) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(LandingPage.SHARED_PREFERENCE_USERID_KEY, Context.MODE_PRIVATE);

        if (preferences.contains(LandingPage.SHARED_PREFERENCE_USERID_KEY)) {
            creatorID = preferences.getInt(LandingPage.SHARED_PREFERENCE_USERID_KEY,-1);
        }
        if (creatorID == -1 & savedInstanceState != null && savedInstanceState.containsKey(LandingPage.SHARED_PREFERENCE_USERID_KEY)) {
            creatorID = savedInstanceState.getInt(LandingPage.SHARED_PREFERENCE_USERID_KEY,-1);
        }
        if (creatorID == -1) {
            creatorID = getIntent().getIntExtra(LandingPage.LANDING_PAGE_USER_ID,-1);
        }
        if (creatorID == -1) {
            return;
        }

    }

    private void toastTextId(int id) {
        String text = "Project successfully created, id is " + id;
        Toast.makeText(CreateProjectPage.this, text, Toast.LENGTH_SHORT).show();
    }

}