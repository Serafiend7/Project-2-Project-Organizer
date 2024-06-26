package com.example.project2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.database.entities.UserID;

import java.util.List;

@Dao
public interface UserIDDAO {
    @Query("SELECT * from " + UserIDDatabase.USER_ID_TABLE + " WHERE username == :username")
    UserID getUserByUsername(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserID... userID);

    @Delete
    void delete(UserID userID);

    @Query("Select * from " + UserIDDatabase.USER_ID_TABLE + " ORDER BY id")
    List<UserID> getAllRecords();

    @Query("DELETE from " + UserIDDatabase.USER_ID_TABLE)
    void deleteAll();

    @Query("SELECT * from " + UserIDDatabase.USER_ID_TABLE + " WHERE id == :id")
    UserID getUserByUserID(Integer id);
}
