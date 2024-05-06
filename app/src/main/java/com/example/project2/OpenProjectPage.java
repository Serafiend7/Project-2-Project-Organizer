package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.ProjectRepository;
import com.example.project2.database.UserIDRepository;
import com.example.project2.database.entities.Project;
import com.example.project2.databinding.OpenProjectPageBinding;

public class OpenProjectPage extends AppCompatActivity {

    OpenProjectPageBinding binding;

    ProjectRepository repository;

    UserIDRepository userRepository;

    private int projectID;

    private int userID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OpenProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ProjectRepository.getRepository(getApplication());
        userRepository = UserIDRepository.getRepository(getApplication());
        binding.PreviousPageButton.setOnClickListener(v -> startActivity(new Intent(OpenProjectPage.this, LandingPage.class)));

        projectID = Integer.parseInt(binding.EnterProjectIdEditTextNumberSigned.getText().toString());
        binding.OpenProjectButton.setOnClickListener(v -> {
            if (!checkProjectID(projectID)) {
                Toast.makeText(OpenProjectPage.this, "Incorrect project id", Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(NotCreatorProjectPage.SHARED_PREFERENCE_PROJECT_ID_KEY,Context.MODE_PRIVATE);
                SharedPreferences.Editor preferencesEditor = preferences.edit();
                preferencesEditor.putInt(NotCreatorProjectPage.SHARED_PREFERENCE_PROJECT_ID_KEY, projectID);
                preferencesEditor.apply();

                startActivity(NotCreatorProjectPage.notCreatorProjectPageIntentFactory(getApplicationContext(),projectID));
                if (isCreator()) {
                    startActivity(new Intent(OpenProjectPage.this, ProjectPage.class));
                }
            }
        });
    }

    private boolean checkProjectID(int id){

        Project project = repository.getProjectByProjectID(id);

        return id != 0 && project != null;
    }
    private boolean isCreator() {
        int creatorID = userRepository.getUserByUserName(repository.getProjectByProjectID(projectID).getCreatorName()).getId();
        return creatorID == userID;
    }

    private void getCurrentID(Bundle savedInstanceState) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(LandingPage.SHARED_PREFERENCE_USERID_KEY, Context.MODE_PRIVATE);

        if (preferences.contains(LandingPage.SHARED_PREFERENCE_USERID_KEY)) {
            userID = preferences.getInt(LandingPage.SHARED_PREFERENCE_USERID_KEY,-1);
        }
        if (userID == -1 & savedInstanceState != null && savedInstanceState.containsKey(LandingPage.SHARED_PREFERENCE_USERID_KEY)) {
            userID = savedInstanceState.getInt(LandingPage.SHARED_PREFERENCE_USERID_KEY,-1);
        }
        if (userID == -1) {
            userID = getIntent().getIntExtra(LandingPage.LANDING_PAGE_USER_ID,-1);
        }

    }



    public static Intent openProjectPageActivityIntentFactory(Context context) {
        return new Intent(context, OpenProjectPage.class);
    }
}
