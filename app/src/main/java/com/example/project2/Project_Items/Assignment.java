package com.example.project2.Project_Items;

import com.example.project2.Database.entities.UserID;

import java.util.HashMap;
import java.util.List;

public class Assignment extends Item {

    private String assignmentDetails;
    private HashMap<UserID,Boolean> completedUsers;

    public Assignment(String name, List<UserID> users, String assignmentDetails, HashMap<UserID, Boolean> completedUsers) {
        super(name, users);
        this.assignmentDetails = assignmentDetails;
        this.completedUsers = completedUsers;
    }

    public String getAssignmentDetails() {
        return assignmentDetails;
    }

    public void setAssignmentDetails(String assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
    }

    public HashMap<UserID, Boolean> getCompletedUsers() {
        return completedUsers;
    }

    public void setCompletedUsers(HashMap<UserID, Boolean> completedUsers) {
        this.completedUsers = completedUsers;
    }
}
