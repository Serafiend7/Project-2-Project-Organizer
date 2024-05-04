package com.example.project2.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.Database.entities.Project;

import java.util.ArrayList;

@Dao
public interface ProjectDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project p);

    @Query("Select * from " + ProjectDatabase.PROJECT_TABLE)
    ArrayList<Project> getAllRecords();
}
