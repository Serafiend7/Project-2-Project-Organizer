package com.example.project2.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project2.MainActivity;
import com.example.project2.database.entities.Project_Items.Assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Assignment.class}, version = 1, exportSchema = false)

public abstract class AssignmentDatabase extends RoomDatabase {
    private static final String DATA_BASE_NAME = "Assignment_database";
    public static final String ASSIGNMENT_TABLE = "assignmentTable";
    private static volatile AssignmentDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AssignmentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AssignmentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AssignmentDatabase.class,
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
            Log.i(MainActivity.ASSIGNMENT_TAG, "ASSIGNMENT DATABASE CREATED!");
        }
    };

    public abstract AssignmentDAO AssignmentDAO();
}
