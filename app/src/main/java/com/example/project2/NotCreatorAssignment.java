package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.AnnouncementRepository;
import com.example.project2.database.AssignmentRepository;
import com.example.project2.database.ProjectRepository;
import com.example.project2.database.entities.Project_Items.Announcement;
import com.example.project2.database.entities.Project_Items.Assignment;
import com.example.project2.databinding.NotCreatorAssignmentBinding;
import com.example.project2.databinding.ProjectPageBinding;

import java.util.List;

public class NotCreatorAssignment extends AppCompatActivity {

    NotCreatorAssignmentBinding binding;

    int userID;

    int projectID;

    ProjectRepository repositoryP;

    AssignmentRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NotCreatorAssignmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repositoryP = ProjectRepository.getRepository(getApplication());
        repository = AssignmentRepository.getRepository(getApplication());
        getUserID(savedInstanceState);
        getProjectID(savedInstanceState);

        listAssignments();

        binding.ListOfAssignments.setMovementMethod(new ScrollingMovementMethod());

        binding.PreviousPageButton.setOnClickListener(v -> startActivity(new Intent(NotCreatorAssignment.this, NotCreatorProjectPage.class)));
    }

    public void listAssignments() {
        List<Integer> announcementsIDs = repositoryP.getProjectByProjectID(projectID).getAnnouncements();
        String yourAssignments = "";

        for (int id : announcementsIDs) {
            Assignment a = repository.getAssignmentByAssignmentID(id);
            if (a.getUsernameList().contains(userID)) {
                yourAssignments += a + "\n\n";
            }
        }

        binding.ListOfAssignments.setText(yourAssignments);
    }

    public void getUserID(Bundle savedInstanceState) {
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

    public void getProjectID(Bundle savedInstanceState) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(NotCreatorProjectPage.SHARED_PREFERENCE_PROJECT_ID_KEY, Context.MODE_PRIVATE);

        if (preferences.contains(NotCreatorProjectPage.SHARED_PREFERENCE_PROJECT_ID_KEY)) {
            projectID = preferences.getInt(NotCreatorProjectPage.SHARED_PREFERENCE_PROJECT_ID_KEY,-1);
        }
        if (projectID == -1 & savedInstanceState != null && savedInstanceState.containsKey(NotCreatorProjectPage.SAVED_INSTANCE_STATE_PROJECT_ID_KEY)) {
            projectID = savedInstanceState.getInt(NotCreatorProjectPage.SAVED_INSTANCE_STATE_PROJECT_ID_KEY,-1);
        }
        if (projectID == -1) {
            projectID = getIntent().getIntExtra(NotCreatorProjectPage.NOT_CREATOR_PROJECT_PAGE_USER_ID,-1);
        }
    }
}
