package com.example.project2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.database.entities.Project_Items.Announcement;

import java.util.List;
@Dao
public interface AnnouncementDAO {
    @Query("SELECT * from " + AnnouncementDatabase.ANNOUNCEMENT_TABLE + " WHERE name == :itemName")
    Announcement getAnnouncementByAnnouncementName(String itemName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Announcement a);

    @Query("Select * from " + AnnouncementDatabase.ANNOUNCEMENT_TABLE)
    List<Announcement> getAllRecords();

    @Query("SELECT * from " + AnnouncementDatabase.ANNOUNCEMENT_TABLE + " WHERE id == :id")
    Announcement getAnnouncementByAnnouncementID(Integer id);
}
