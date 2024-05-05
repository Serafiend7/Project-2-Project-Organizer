package com.example.project2.Project_Items;

import com.example.project2.database.entities.UserID;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

public class Assignment extends Item {

    private String assignmentDetails;
    private HashMap<String,Boolean> completedUsers;
    private LocalDateTime dueDate;

    public Assignment(String name, ArrayList<String> users, String assignmentDetails, LocalDateTime dueDate) {
        super(name, users);
        this.assignmentDetails = assignmentDetails;
        completedUsers = new HashMap<>();
        for (String user : users) {
            completedUsers.put(user,Boolean.FALSE);
        }
        this.dueDate = dueDate;
    }

    public Assignment(String name, ArrayList<String> users, String assignmentDetails, HashMap<String, Boolean> completedUsers, LocalDateTime dueDate) {
        super(name, users);
        this.assignmentDetails = assignmentDetails;
        this.completedUsers = completedUsers;
        this.dueDate = dueDate;
    }

    public String getAssignmentDetails() {
        return assignmentDetails;
    }
    public void setAssignmentDetails(String assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
    }
    public HashMap<String, Boolean> getCompletedUsers() {
        return completedUsers;
    }
    public void setCompletedUsers(HashMap<String, Boolean> completedUsers) {
        this.completedUsers = completedUsers;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    public int totalCompleted() {
        int total = 0;
        for(Boolean b : completedUsers.values()) {
            if (b) {
                total++;
            }
        }
        return total;
    }
    public int totalMissing() {
        int total = 0;
        for (Boolean b : completedUsers.values()) {
            if (!b) {
                total++;
            }
        }
        return total;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(assignmentDetails, that.assignmentDetails) && Objects.equals(completedUsers, that.completedUsers);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), assignmentDetails, completedUsers);
    }
}
