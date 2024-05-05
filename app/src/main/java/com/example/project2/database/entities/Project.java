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
    private int creatorID;
    private ArrayList<Integer> sharedUserIDs;
    private ArrayList<Assignment> assignments;

    private ArrayList<Announcement> announcements;
    private LocalDateTime dueDate;

    @Deprecated
    public Project(){};

    public Project(String name,int creatorID, ArrayList<Integer> sharedUserIDS, ArrayList<Assignment> assignments, ArrayList<Announcement> announcements, LocalDateTime dueDate) {
        this.name = name;
        this.creatorID = creatorID;
        this.sharedUserIDs = sharedUserIDS;
        this.assignments = assignments;
        this.announcements = announcements;
        this.dueDate = dueDate;
    }

    public Project(String name,int creatorID, ArrayList<Integer> sharedUserIDs, LocalDateTime dueDate) {
        this.name = name;
        this.creatorID = creatorID;
        this.sharedUserIDs = sharedUserIDs;
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
    public int getCreatorID() {
        return creatorID;
    }
    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }
    public ArrayList<Integer> getSharedUserIDs() {
        return sharedUserIDs;
    }
    public void setSharedUserIDs(ArrayList<Integer> sharedUserIDs) {
        this.sharedUserIDs = sharedUserIDs;
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
        return id == project.id && creatorID == project.creatorID && Objects.equals(name, project.name) && Objects.equals(sharedUserIDs, project.sharedUserIDs) && Objects.equals(assignments, project.assignments) && Objects.equals(announcements, project.announcements) && Objects.equals(dueDate, project.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creatorID, sharedUserIDs, assignments, announcements, dueDate);
    }
}
