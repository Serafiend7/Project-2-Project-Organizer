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
    private LocalDateTime dueDate;

    public Assignment() {
        super("", new ArrayList<>());
        assignmentDetails = "";
        dueDate = LocalDateTime.parse("0000-00-00T00:00");
    }

    public Assignment(String name, ArrayList<Integer> users, String assignmentDetails, LocalDateTime dueDate) {
        super(name, users);
        this.assignmentDetails = assignmentDetails;
        this.dueDate = dueDate;
    }

    public String getAssignmentDetails() {
        return assignmentDetails;
    }
    public void setAssignmentDetails(String assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Assignment that = (Assignment) o;
        return id == that.id && Objects.equals(assignmentDetails, that.assignmentDetails) && Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, assignmentDetails, dueDate);
    }

    @Override
    public String toString() {
        return "Assignment: " + getName() + "\n" + assignmentDetails + '\n' + "Due Date: " + dueDate.toString();
    }
}
