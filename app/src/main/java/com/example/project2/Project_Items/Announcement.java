package com.example.project2.Project_Items;

import com.example.project2.Database.entities.UserID;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Announcement extends Item{

    private String message;
    private HashMap<UserID,Boolean> usersViewed;

    public Announcement(String name, List<UserID> users, String message) {
        super(name, users);
        this.message = message;
        usersViewed = new HashMap<>();
        for (UserID user : users) {
            usersViewed.put(user,Boolean.FALSE);
        }
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
