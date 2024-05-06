package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.ProjectRepository;
import com.example.project2.database.entities.Project;
import com.example.project2.databinding.NotCreatorProjectPageBinding;

import java.util.Locale;

public class NotCreatorProjectPage extends AppCompatActivity {

    NotCreatorProjectPageBinding binding;

    static final String SAVED_INSTANCE_STATE_PROJECT_ID_KEY = "com.example.project2.SAVED_INSTANCE_STATE_PROJECT_ID_KEY";
    static final String NOT_CREATOR_PROJECT_PAGE_USER_ID = "com.example.project2.NOT_CREATOR_PROJECT_PAGE_PROJECT_ID";
    static final String SHARED_PREFERENCE_PROJECT_ID_KEY = "com.example.project2.SHARED_PREFERENCE_PROJECT_ID_KEY";
    static final String PROJECT_ID_EXTRA_KEY = "NotCreatorProjectPage_Received_Project_Id";

    private int projectID;

    ProjectRepository repository;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NotCreatorProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        projectID(savedInstanceState);
        Project project = repository.getProjectByProjectID(projectID);

        binding.NotCreatorProjectPageTextView.setText(String.format(Locale.ENGLISH,"%s",project.getName()));
        binding.ProjectIdTextView.setText(String.format(Locale.ENGLISH,"ID: %s",projectID));

        binding.OpenAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotCreatorProjectPage.this, NotCreatorAnnouncements.class));               }
        });

        binding.ViewAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotCreatorProjectPage.this, NotCreatorAssignment.class));            }
        });

    }

    private void projectID(Bundle savedInstanceState) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_PROJECT_ID_KEY, Context.MODE_PRIVATE);

        if (preferences.contains(SHARED_PREFERENCE_PROJECT_ID_KEY)) {
            projectID = preferences.getInt(SHARED_PREFERENCE_PROJECT_ID_KEY,-1);
        }
        if (projectID == -1 & savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_STATE_PROJECT_ID_KEY)) {
            projectID = savedInstanceState.getInt(SAVED_INSTANCE_STATE_PROJECT_ID_KEY,-1);
        }
        if (projectID == -1) {
            projectID = getIntent().getIntExtra(NOT_CREATOR_PROJECT_PAGE_USER_ID,-1);
        }

    }

    public static Intent notCreatorProjectPageIntentFactory(Context context, int projectID) {
        Intent intent = new Intent(context, NotCreatorProjectPage.class);
        intent.putExtra(PROJECT_ID_EXTRA_KEY,projectID);
        return intent;
    }
}
