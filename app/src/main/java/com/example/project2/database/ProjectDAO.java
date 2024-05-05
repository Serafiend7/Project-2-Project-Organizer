package com.example.project2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.database.entities.Project;
import com.example.project2.database.entities.UserID;

import java.util.List;

@Dao
public interface ProjectDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project p);

    @Query("Select * from " + ProjectDatabase.PROJECT_TABLE)
    List<Project> getAllRecords();

//    @Query("SELECT * from " + UserIDDatabase.USER_ID_TABLE + " WHERE id == :id")
//    Project getProjectByProjectID(Integer id);
}
