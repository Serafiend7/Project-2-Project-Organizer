package com.example.project2.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project2.database.entities.UserID;
import com.example.project2.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserID.class}, version = 1, exportSchema = false)
public abstract class UserIDDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "UserIDDatabase";
    public static final String USER_ID_TABLE = "userIDTable";

    private static volatile UserIDDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static UserIDDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (UserIDDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                                    UserIDDatabase.class, DATABASE_NAME)
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
            Log.i(MainActivity.TAG, "Database Created!");
            databaseWriteExecutor.execute(() -> {
                UserIDDAO dao = INSTANCE.userIDDAO();
                dao.deleteAll();
                UserID admin = new UserID("admin1", "admin1", true);
                dao.insert(admin);

                UserID testUser1 = new UserID("testUser1", "testUser1", false);
                dao.insert(testUser1);

                UserID testUser2 = new UserID("testUser2", "testUser2", false);
                dao.insert(testUser2);

            });
        }
    };

    public abstract UserIDDAO userIDDAO();
}
