package com.example.project2.database.entities;


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

    private int creatorID;
    private ArrayList<Integer> sharedUserIDs;
    private ArrayList<Item> items;
    private LocalDateTime dueDate;

    public Project(int creatorID, ArrayList<Integer> sharedUserIDS, ArrayList<Item> items, LocalDateTime dueDate) {
        this.creatorID = creatorID;
        this.sharedUserIDs = sharedUserIDS;
        this.items = items;
        this.dueDate = dueDate;
    }

    public int getCreator() {
        return creatorID;
    }
    public void setCreator(int creatorID) {
        this.creatorID = creatorID;
    }
    public ArrayList<Integer> getSharedUserIDs() {
        return sharedUserIDs;
    }
    public void setSharedUsers(ArrayList<Integer> sharedUserIDs) {
        this.sharedUserIDs = sharedUserIDs;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
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
        items.add(a);
    }
    public void deleteAssignment(Assignment a) {
        items.remove(a);
    }
    public void addAnnouncement(Announcement a) {
        items.add(a);
    }
    public void deleteAnnouncement(Announcement a) {
        items.remove(a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Objects.equals(creatorID, project.creatorID) && Objects.equals(sharedUserIDs, project.sharedUserIDs) && Objects.equals(items, project.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creatorID, sharedUserIDs, items);
    }
}
