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

    private UserID creator;
    private ArrayList<UserID> sharedUsers;
    private ArrayList<Item> items;
    private LocalDateTime dueDate;
    private ArrayList<String> name;

    public Project(UserID creator, ArrayList<UserID> sharedUsers, ArrayList<Item> items, LocalDateTime dueDate, ArrayList<String> name) {
        this.creator = creator;
        this.sharedUsers = sharedUsers;
        this.items = items;
        this.dueDate = dueDate;
        this.name = name;
    }

    public UserID getCreator() {
        return creator;
    }
    public void setCreator(UserID creator) {
        this.creator = creator;
    }
    public ArrayList<UserID> getSharedUsers() {
        return sharedUsers;
    }
    public void setSharedUsers(ArrayList<UserID> sharedUsers) {
        this.sharedUsers = sharedUsers;
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
        return id == project.id && Objects.equals(creator, project.creator) && Objects.equals(sharedUsers, project.sharedUsers) && Objects.equals(items, project.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creator, sharedUsers, items);
    }
}
