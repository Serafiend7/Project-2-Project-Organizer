package com.example.project2.Project_Items;

import com.example.project2.Database.entities.UserID;

import java.util.List;
import java.util.Objects;

public abstract class Item {

    private String name;
    private List<UserID> users;


    public Item(String name, List<UserID> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<UserID> getUsers() {
        return users;
    }
    public void setUsers(List<UserID> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(users, item.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users);
    }
}
