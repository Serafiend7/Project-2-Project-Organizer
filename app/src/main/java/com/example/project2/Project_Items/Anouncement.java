package com.example.project2.Project_Items;

import com.example.project2.Database.entities.UserID;

import java.util.HashMap;
import java.util.List;

public class Anouncement extends Item{

    private String message;
    private HashMap<UserID,Boolean> usersViewed;

    public Anouncement(String name, List<UserID> users, String message, HashMap<UserID, Boolean> usersViewed) {
        super(name, users);
        this.message = message;
        this.usersViewed = usersViewed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<UserID, Boolean> getUsersViewed() {
        return usersViewed;
    }

    public void setUsersViewed(HashMap<UserID, Boolean> usersViewed) {
        this.usersViewed = usersViewed;
    }

    public int getViewCount() {
        return usersViewed.size();
    }
}
