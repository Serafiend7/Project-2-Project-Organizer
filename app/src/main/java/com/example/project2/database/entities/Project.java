package com.example.project2.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.database.ProjectDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Entity(tableName = ProjectDatabase.PROJECT_TABLE)
public class Project {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String creatorName;
    private ArrayList<Integer> sharedUserIDs;
    private ArrayList<Integer> assignments;

    private ArrayList<Integer> announcements;
    private LocalDateTime dueDate;

    @Deprecated
    public Project(){};

    public Project(String name,String creatorName, ArrayList<Integer> sharedUserIDs, ArrayList<Integer> assignments, ArrayList<Integer> announcements, LocalDateTime dueDate) {
        this.name = name;
        this.creatorName = creatorName;
        this.sharedUserIDs = sharedUserIDs;
        this.assignments = assignments;
        this.announcements = announcements;
        this.dueDate = dueDate;
    }

    public Project(String name,String creatorName, ArrayList<Integer> sharedUserIDs, LocalDateTime dueDate) {
        this.name = name;
        this.creatorName = creatorName;
        this.sharedUserIDs = sharedUserIDs;
        this.dueDate = dueDate;
        assignments = new ArrayList<Integer>();
        announcements = new ArrayList<Integer>();
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
    public ArrayList<Integer> getSharedUserIDs() {
        return sharedUserIDs;
    }
    public void setSharedUserIDs(ArrayList<Integer> sharedUserIDs) {
        this.sharedUserIDs = sharedUserIDs;
    }
    public ArrayList<Integer> getAssignments() {
        return assignments;
    }
    public void setAssignments(ArrayList<Integer> assignments) {
        this.assignments = assignments;
    }
    public ArrayList<Integer> getAnnouncements() {
        return announcements;
    }
    public void setAnnouncements(ArrayList<Integer> announcements) {
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

    public void addAssignment(Integer a) {
        assignments.add(a);
    }
    public void deleteAssignment(Integer a) {
        assignments.remove(a);
    }
    public void addAnnouncement(Integer a) {
        announcements.add(a);
    }
    public void deleteAnnouncement(Integer a) {
        announcements.remove(a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Objects.equals(name, project.name) && Objects.equals(creatorName, project.creatorName) && Objects.equals(sharedUserIDs, project.sharedUserIDs) && Objects.equals(assignments, project.assignments) && Objects.equals(announcements, project.announcements) && Objects.equals(dueDate, project.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creatorName, sharedUserIDs, assignments, announcements, dueDate);
    }

//    @Override
//    public String toString() {
//        String returnString = "";
//        returnString += "Project ID: " + this.id + "\n Project Name: " + this.name +
//                "\n Shared Users: \n" ;
//        for (String name : sharedUserNames) {
//            returnString += name + "\n";
//        }
//        returnString += "Assignments: \n";
//        for (String assignments : assignments){
//            returnString += assignments + "\n";
//        }
//        returnString += "Announcements: \n";
//        for (String announcements : announcements) {
//            returnString += announcements + "\n";
//        }
//        returnString += "Due Date: " + dueDate.toString();
//        return returnString;
//    }
}
