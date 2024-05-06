package com.example.project2.database.entities.Project_Items;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.project2.database.AssignmentDatabase;
import com.example.project2.database.typeConverters.AssignmentTypeConverters;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

@TypeConverters(AssignmentTypeConverters.class)
@Entity(tableName = AssignmentDatabase.ASSIGNMENT_TABLE)
public class Assignment extends Item {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String assignmentDetails;
    private HashMap<Integer,Boolean> completedUsers;
    private LocalDateTime dueDate;

    public Assignment() {
        super("", new ArrayList<>());
        assignmentDetails = "";
        completedUsers = new HashMap<>();
        dueDate = LocalDateTime.parse("0000-00-00T00:00");
    }

    public Assignment(String name, ArrayList<Integer> users, String assignmentDetails, LocalDateTime dueDate) {
        super(name, users);
        this.assignmentDetails = assignmentDetails;
        completedUsers = new HashMap<>();
        for (Integer user : users) {
            completedUsers.put(user,Boolean.FALSE);
        }
        this.dueDate = dueDate;
    }

    public Assignment(String name, ArrayList<Integer> users, String assignmentDetails, HashMap<Integer, Boolean> completedUsers, LocalDateTime dueDate) {
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
    public HashMap<Integer, Boolean> getCompletedUsers() {
        return completedUsers;
    }
    public void setCompletedUsers(HashMap<Integer, Boolean> completedUsers) {
        this.completedUsers = completedUsers;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
