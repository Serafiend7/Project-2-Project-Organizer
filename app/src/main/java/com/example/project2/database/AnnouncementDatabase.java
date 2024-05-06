package com.example.project2.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project2.MainActivity;
import com.example.project2.database.entities.Project_Items.Announcement;
import com.example.project2.database.typeConverters.AnnouncementTypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(AnnouncementTypeConverters.class)
@Database(entities = {Announcement.class}, version = 1, exportSchema = false)
public abstract class AnnouncementDatabase extends RoomDatabase {
    private static final String DATA_BASE_NAME = "Announcement_database";
    public static final String ANNOUNCEMENT_TABLE = "announcementTable";
    private static volatile AnnouncementDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AnnouncementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AnnouncementDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AnnouncementDatabase.class,
                                    DATA_BASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();

                }
            }
        }
        return INSTANCE;
    }


    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MainActivity.ANNOUNCEMENT_TAG, "ANNOUNCEMENT DATABASE CREATED!");
        }
    };

    public abstract AnnouncementDAO AnnouncementDAO();
}
