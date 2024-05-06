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


    public Announcement() {
        super("",new ArrayList<>());
        message = "";
    }

    public Announcement(String name, ArrayList<Integer> users, String message) {
        super(name, users);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Announcement that = (Announcement) o;
        return id == that.id && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, message);
    }

    @Override
    public String toString() {
        return "Announcement: " + getName() + "\n" + message + '\n';
    }
}
