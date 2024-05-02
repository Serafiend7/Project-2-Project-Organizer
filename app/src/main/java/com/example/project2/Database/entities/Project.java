package com.example.project2.Database.entities;


import com.example.project2.Project_Items.Anouncement;
import com.example.project2.Project_Items.Assignment;
import com.example.project2.Project_Items.Item;
import com.example.project2.Project_Items.Link;

import java.util.List;

public class Project {

    private UserID creator;
    private List<UserID> sharedUsers;
    private List<Item> items;

    public Project(UserID creator, List<UserID> sharedUsers, List<Item> items) {
        this.creator = creator;
        this.sharedUsers = sharedUsers;
        this.items = items;
    }
    public void addAssignment(Assignment a) {
        items.add(a);
    }
    public void deleteAssignment(Assignment a) {
        items.remove(a);
    }
    public void addAnouncement(Anouncement a) {
        items.add(a);
    }
    public void deleteAnnouncement(Anouncement a) {
        items.remove(a);
    }
    public void addLink(Link l) {
        items.add(l);
    }
    public void deleteLink(Link l) {
        items.remove(l);
    }
}
