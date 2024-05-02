package com.example.project2.Project_Items;

import com.example.project2.Database.entities.UserID;

import java.util.List;

public abstract class Item {

    private String name;
    private List<UserID> users;


    public Item(String name, List<UserID> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserID> getUsers() {
        return users;
    }

    public void setUsers(List<UserID> users) {
        this.users = users;
    }
}
