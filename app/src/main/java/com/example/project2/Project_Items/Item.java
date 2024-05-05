package com.example.project2.Project_Items;

import com.example.project2.database.entities.UserID;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Item {

    private String name;
    private ArrayList<String> usernameList;


    public Item(String name, ArrayList<String> usernameList) {
        this.name = name;
        this.usernameList = usernameList;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<String> getUsers() {
        return usernameList;
    }
    public void setUsers(ArrayList<String> usernameList) {
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
