package com.example.project2.Project_Items;

import com.example.project2.database.entities.UserID;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

public class Announcement extends Item{

    private String message;
    private HashMap<String,Boolean> usersViewed;

    public Announcement(String name, ArrayList<String> users, String message) {
        super(name, users);
        this.message = message;
        usersViewed = new HashMap<>();
        for (String user : users) {
            usersViewed.put(user,Boolean.FALSE);
        }
    }

    public Announcement(String name, ArrayList<String> userIDlist, String message, HashMap<String, Boolean> usersViewed) {
        super(name, userIDlist);
        this.message = message;
        this.usersViewed = usersViewed;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public HashMap<String, Boolean> getUsersViewed() {
        return usersViewed;
    }
    public void setUsersViewed(HashMap<String, Boolean> usersViewed) {
        this.usersViewed = usersViewed;
    }
    public int getViewCount() {
        int views = 0;
        for (String user : usersViewed.keySet()) {
            if (Boolean.TRUE.equals(usersViewed.get(user))) {
                views++;
            }
        }
        return views;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Announcement that = (Announcement) o;
        return Objects.equals(message, that.message) && Objects.equals(usersViewed, that.usersViewed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), message, usersViewed);
    }
}
