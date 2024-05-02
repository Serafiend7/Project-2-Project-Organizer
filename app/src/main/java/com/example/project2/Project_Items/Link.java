package com.example.project2.Project_Items;

import com.example.project2.Database.entities.UserID;

import java.util.List;

public class Link extends Item{

    public String link;

    public Link(String name, List<UserID> users, String link) {
        super(name, users);
        this.link = link;
    }
}
