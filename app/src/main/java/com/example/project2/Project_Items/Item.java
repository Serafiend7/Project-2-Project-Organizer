package com.example.project2.Project_Items;

import com.example.project2.database.entities.UserID;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Item {

    private String name;
    private ArrayList<Integer> userIDlist;


    public Item(String name, ArrayList<Integer> userIDlist) {
        this.name = name;
        this.userIDlist = userIDlist;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Integer> getUsers() {
        return userIDlist;
    }
    public void setUsers(ArrayList<Integer> userIDlist) {
        this.userIDlist = userIDlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(userIDlist, item.userIDlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userIDlist);
    }
}
