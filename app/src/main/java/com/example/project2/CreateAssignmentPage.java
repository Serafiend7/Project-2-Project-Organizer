package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.AssignmentRepository;
import com.example.project2.database.ProjectRepository;
import com.example.project2.database.UserIDRepository;
import com.example.project2.database.entities.Project_Items.Assignment;
import com.example.project2.database.entities.UserID;
import com.example.project2.databinding.CreateAccountPageBinding;
import com.example.project2.databinding.CreateAssignmentPageBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreateAssignmentPage extends AppCompatActivity {
//    CreateAssignmentPageBinding binding;
//
//    private AssignmentRepository repository;
//
//    private ProjectRepository repositoryP;
//
//    private UserIDRepository repositoryU;
//
//    private int projectID;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = CreateAssignmentPageBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        repositoryP = ProjectRepository.getRepository(getApplication());
//        repository = AssignmentRepository.getRepository(getApplication());
//        repositoryU = UserIDRepository.getRepository(getApplication());
//
//        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CreateAssignmentPage.this, ProjectPage.class));
//            }
//        });
//
//        binding.CreateAssignmentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!binding.EnterPasswordEditTextNumberSigned.getText().toString().equals(binding.ConfirmPasswordEditTextNumberSigned.getText().toString())){
//                    Toast.makeText(CreateAccountPage.this, "Passwords do not match, please re-enter your password", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    createAccount(binding.EnterUsernameEditTextNumberSigned.getText().toString(), binding.EnterPasswordEditTextNumberSigned.getText().toString(), binding.ConfirmPasswordEditTextNumberSigned.getText().toString());
//                    Toast.makeText(CreateAccountPage.this, "Account successfully created", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public void createAssingment(){
//        String name = binding.EnterAssignmentNameEditTextNumberSigned.getText().toString();
//
//        ArrayList<Integer> sharedUserIDs = new ArrayList<>();
//        String users = binding.EnterSharedUsersEditTextNumberSigned.getText().toString();
//        String[] names = users.split(",");
//
//        for (String s : names) {
//            sharedUserIDs.add(repositoryU.getUserByUserName(s).getId());
//        }
//
//        String assignmentDetails = binding.EnterAssignmentDetailsEditTextNumberSigned.getText().toString();
//
//        String date = binding.EnterDueDateEditTextNumberSigned.getText().toString();
//        String[] v = date.split("/");
//        date = v[2] + "-";
//        if (Integer.parseInt(v[0]) < 10) {
//            date += "0";
//        }
//        date += v[0] + "-";
//        if (Integer.parseInt(v[1]) < 10) {
//            date+= "0";
//        }
//        date += v[1] + " 00:00";
//        LocalDateTime dueDate = LocalDateTime.parse(date,formatter);
//
//        Assignment a = new Assignment(name, sharedUserIDs,assignmentDetails,dueDate);
//        repository.insertAssignment(a);
//
//        repositoryP.getProjectByProjectID(projectID).addAssignment(repository.getA);
//
//
//        UserID userId = new UserID(username, password, false);
//        UserID currentUser = repository.getUserByUserName(username);
//        if (currentUser != null){
//            userId.setId(currentUser.getId());
//        }
//        repository.insertUserID(userId);
//    }
//
//    public void getProjectID(Bundle savedInstanceState) {
//        SharedPreferences preferences = getApplicationContext().getSharedPreferences(NotCreatorProjectPage.SHARED_PREFERENCE_PROJECT_ID_KEY, Context.MODE_PRIVATE);
//
//        if (preferences.contains(NotCreatorProjectPage.SHARED_PREFERENCE_PROJECT_ID_KEY)) {
//            projectID = preferences.getInt(NotCreatorProjectPage.SHARED_PREFERENCE_PROJECT_ID_KEY,-1);
//        }
//        if (projectID == -1 & savedInstanceState != null && savedInstanceState.containsKey(NotCreatorProjectPage.SAVED_INSTANCE_STATE_PROJECT_ID_KEY)) {
//            projectID = savedInstanceState.getInt(NotCreatorProjectPage.SAVED_INSTANCE_STATE_PROJECT_ID_KEY,-1);
//        }
//        if (projectID == -1) {
//            projectID = getIntent().getIntExtra(NotCreatorProjectPage.NOT_CREATOR_PROJECT_PAGE_USER_ID,-1);
//        }
//    }
}
