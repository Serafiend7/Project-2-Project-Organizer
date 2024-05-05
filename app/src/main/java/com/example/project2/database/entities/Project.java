package com.example.project2.database.entities;


import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.database.ProjectDatabase;
import com.example.project2.Project_Items.Announcement;
import com.example.project2.Project_Items.Assignment;
import com.example.project2.Project_Items.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(tableName = ProjectDatabase.PROJECT_TABLE)
public class Project {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String creatorName;
    private ArrayList<String> sharedUserNames;
    private ArrayList<Assignment> assignments;

    private ArrayList<Announcement> announcements;
    private LocalDateTime dueDate;

    @Deprecated
    public Project(){};

    public Project(String name,String creatorName, ArrayList<String> sharedUserNames, ArrayList<Assignment> assignments, ArrayList<Announcement> announcements, LocalDateTime dueDate) {
        this.name = name;
        this.creatorName = creatorName;
        this.sharedUserNames = sharedUserNames;
        this.assignments = assignments;
        this.announcements = announcements;
        this.dueDate = dueDate;
    }

    public Project(String name,String creatorName, ArrayList<String> sharedUserNames, LocalDateTime dueDate) {
        this.name = name;
        this.creatorName = creatorName;
        this.sharedUserNames = sharedUserNames;
        this.dueDate = dueDate;
        assignments = new ArrayList<>();
        announcements = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public ArrayList<String> getSharedUserNames() {
        return sharedUserNames;
    }
    public void setSharedUserNames(ArrayList<String> sharedUserNames) {
        this.sharedUserNames = sharedUserNames;
    }
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }
    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }
    public void setAnnouncements(ArrayList<Announcement> announcements) {
        this.announcements = announcements;
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

    public void addAssignment(Assignment a) {
        assignments.add(a);
    }
    public void deleteAssignment(Assignment a) {
        assignments.remove(a);
    }
    public void addAnnouncement(Announcement a) {
        announcements.add(a);
    }
    public void deleteAnnouncement(Announcement a) {
        announcements.remove(a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && creatorName == project.creatorName && Objects.equals(name, project.name) && Objects.equals(sharedUserNames, project.sharedUserNames) && Objects.equals(assignments, project.assignments) && Objects.equals(announcements, project.announcements) && Objects.equals(dueDate, project.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creatorName, sharedUserNames, assignments, announcements, dueDate);
    }

    @Override
    public String toString() {
        String returnString = "";
        returnString += "Project ID: " + this.id + "\n Project Name: " + this.name +
                "\n Shared Users: \n" ;
        for (String name : sharedUserNames) {
            returnString += name + "\n";
        }
        returnString += "Assignments: \n";
        for (Assignment assignments : assignments){
            returnString += assignments.getName() + "\n";
        }
        returnString += "Announcements: \n";
        for (Announcement announcements : announcements) {
            returnString += announcements.getName() + "\n";
        }
        returnString += "Due Date: " + dueDate.toString();
        return returnString;
    }
}
