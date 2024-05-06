package com.example.project2.database.entities.Project_Items;

import androidx.room.TypeConverters;

import com.example.project2.database.typeConverters.AssignmentTypeConverters;

import java.util.ArrayList;
import java.util.Objects;

@TypeConverters(AssignmentTypeConverters.class)
public abstract class Item {

    private String name;
    private ArrayList<Integer> usernameList;

    public Item(String name, ArrayList<Integer> usernameList) {
        this.name = name;
        this.usernameList = usernameList;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Integer> getUsernameList() {
        return usernameList;
    }
    public void setUsernameList(ArrayList<Integer> usernameList) {
            this.usernameList = usernameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(usernameList, item.usernameList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, usernameList);
    }
}
