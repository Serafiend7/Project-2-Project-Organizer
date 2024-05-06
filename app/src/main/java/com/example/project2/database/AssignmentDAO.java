package com.example.project2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.database.entities.Project_Items.Assignment;

import java.util.List;

@Dao
public interface AssignmentDAO {

    @Query("SELECT * from " + AssignmentDatabase.ASSIGNMENT_TABLE + " WHERE name == :itemName")
    Assignment getAssignmentByAssignmentName(String itemName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Assignment a);

    @Query("Select * from " + AssignmentDatabase.ASSIGNMENT_TABLE)
    List<Assignment> getAllRecords();

    @Query("SELECT * from " + AssignmentDatabase.ASSIGNMENT_TABLE + " WHERE id == :id")
    Assignment getAssignmentByAssignmentID(Integer id);
}
