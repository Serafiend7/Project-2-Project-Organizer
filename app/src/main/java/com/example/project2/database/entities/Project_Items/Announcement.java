package com.example.project2.database.entities.Project_Items;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.database.AnnouncementDatabase;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

@Entity(tableName = AnnouncementDatabase.ANNOUNCEMENT_TABLE)
public class Announcement extends Item{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String message;
    private HashMap<Integer,Boolean> usersViewed;



    public Announcement() {
        super("",new ArrayList<>());
        message = "";
        usersViewed = new HashMap<>();
    }

    public Announcement(String name, ArrayList<Integer> users, String message) {
        super(name, users);
        this.message = message;
        usersViewed = new HashMap<>();
        for (Integer user : users) {
            usersViewed.put(user,Boolean.FALSE);
        }
    }

    public Announcement(String name, ArrayList<Integer> users, String message, HashMap<Integer, Boolean> usersViewed) {
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
    public HashMap<Integer, Boolean> getUsersViewed() {
        return usersViewed;
    }
    public void setUsersViewed(HashMap<Integer, Boolean> usersViewed) {
        this.usersViewed = usersViewed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewCount() {
        int views = 0;
        for (Integer user : usersViewed.keySet()) {
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
