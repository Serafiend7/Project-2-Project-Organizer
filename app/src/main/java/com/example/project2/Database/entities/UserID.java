package com.example.project2.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity (tableName = UserIDDatabase.USER_ID_TABLE)
public class UserID {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String username;
    private String password;
    private boolean isAdmin = false;

    public UserID(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID = (UserID) o;
        return Objects.equals(username, userID.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, isAdmin);
    }

    @Override
    @NonNull
    public String toString() {
        return "User ID: " + this.id + "\nUsername: " + this.username + "\nPassword: " + this.password + "\nAdmin Status: " + this.isAdmin;
    }
}