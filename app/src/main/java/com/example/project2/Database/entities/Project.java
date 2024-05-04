package com.example.project2.Database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.Project_Items.Announcement;
import com.example.project2.Project_Items.Assignment;
import com.example.project2.Project_Items.Item;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity(tableName = "project")
public class Project {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private UserID creator;
    private List<UserID> sharedUsers;
    private List<Item> items;
    private LocalDate dueDate;

    public Project(UserID creator, List<UserID> sharedUsers, List<Item> items, LocalDate dueDate) {
        this.creator = creator;
        this.sharedUsers = sharedUsers;
        this.items = items;
        this.dueDate = dueDate;
    }

    public UserID getCreator() {
        return creator;
    }
    public void setCreator(UserID creator) {
        this.creator = creator;
    }
    public List<UserID> getSharedUsers() {
        return sharedUsers;
    }
    public void setSharedUsers(List<UserID> sharedUsers) {
        this.sharedUsers = sharedUsers;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
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
