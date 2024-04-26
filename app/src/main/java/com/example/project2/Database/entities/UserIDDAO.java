package com.example.project2.Database.entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserIDDAO {
//TODO: FIX THIS
    @Query("SELECT * from " + UserIDDatabase.USER_ID_TABLE + " WHERE username == :username")
    UserID getUserByUsername(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserID... userID);

    @Delete
    void delete(UserID userID);

    @Insert
    void insert(UserID userID);

    @Query("Select * from " + UserIDDatabase.USER_ID_TABLE + " ORDER BY username")
    List<UserID> getAllRecords();

    @Query("DELETE from " + UserIDDatabase.USER_ID_TABLE)
    void deleteAll();
}
