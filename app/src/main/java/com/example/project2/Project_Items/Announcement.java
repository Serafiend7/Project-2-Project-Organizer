package com.example.project2.Project_Items;

import com.example.project2.database.entities.UserID;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

public class Announcement extends Item{

    private String message;
    private HashMap<Integer,Boolean> usersViewed;

    public Announcement(String name, ArrayList<Integer> users, String message) {
        super(name, users);
        this.message = message;
        usersViewed = new HashMap<>();
        for (Integer user : users) {
            usersViewed.put(user,Boolean.FALSE);
        }
    }

    public Announcement(String name, ArrayList<Integer> userIDlist, String message, HashMap<Integer, Boolean> usersViewed) {
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
    public HashMap<Integer, Boolean> getUsersViewed() {
        return usersViewed;
    }
    public void setUsersViewed(HashMap<Integer, Boolean> usersViewed) {
        this.usersViewed = usersViewed;
    }
    public int getViewCount() {
        return usersViewed.size();
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
