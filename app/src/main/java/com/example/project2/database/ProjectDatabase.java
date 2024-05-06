package com.example.project2.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project2.database.entities.Project;
import com.example.project2.MainActivity;
import com.example.project2.database.typeConverters.ProjectTypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(ProjectTypeConverters.class)
@Database(entities = {Project.class}, version = 3, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {

    private static final String DATA_BASE_NAME = "Project_database";
    public static final String PROJECT_TABLE = "projectTable";
    private static volatile ProjectDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ProjectDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProjectDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ProjectDatabase.class,
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
            Log.i(MainActivity.PROJECT_TAG, "PROJECT DATABASE CREATED!");
        }
    };

    public abstract ProjectDAO projectDAO();
}