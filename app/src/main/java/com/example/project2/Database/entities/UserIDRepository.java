package com.example.project2.Database.entities;

import android.app.Application;
import android.util.Log;

import com.example.project2.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UserIDRepository {

    private UserIDDAO userIDDAO;
    private ArrayList<UserID> allIDs;

    public UserIDRepository(Application application){
        UserIDDatabase db = UserIDDatabase.getDatabase(application);
        this.userIDDAO = db.userIDDAO();
        this.allIDs = (ArrayList<UserID>) this.userIDDAO.getAllRecords();
    }

    public static UserIDRepository getRepository(Application application) {
        Future<UserIDRepository> future = UserIDDatabase.databaseWriteExecutor.submit(
                new Callable<UserIDRepository>() {
                    @Override
                    public UserIDRepository call() throws Exception {
                        return new UserIDRepository(application);
                    }
                }
        );
        try {
            return future.get();
        }catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Problem getting UserIDRepository, thread error");
        }
        return null;
    }

    public UserID getUserByUserID(Integer id) {
        Future<UserID> future = UserIDDatabase.databaseWriteExecutor.submit(
                new Callable<UserID>() {
                    @Override
                    public UserID call() throws Exception {
                        return userIDDAO.getUserByUserID(id);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.TAG, "Problem when getting user by userID");
        }
        return null;
    }
    public UserID getUserByUserName(String username) {
        Future<UserID> future = UserIDDatabase.databaseWriteExecutor.submit(
                new Callable<UserID>() {
                    @Override
                    public UserID call() throws Exception {
                        return userIDDAO.getUserByUsername(username);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.TAG, "Problem when getting user by username");
        }
        return null;
    }

    public ArrayList<UserID> getAllIDs() {
        Future<ArrayList<UserID>> future = UserIDDatabase.databaseWriteExecutor.submit(new Callable<ArrayList<UserID>>() {
            @Override
            public ArrayList<UserID> call() throws Exception {
                return (ArrayList<UserID>) userIDDAO.getAllRecords();
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
            Log.i(MainActivity.TAG, "Problem when getting all UserIDs in the repository");
        }
        return null;
    }

    public void insertUserID(UserID... userID){
        UserIDDatabase.databaseWriteExecutor.execute(() -> {
            userIDDAO.insert(userID);
        });
    }

    public void delete(UserID userId){
        UserIDDatabase.databaseWriteExecutor.execute(() -> {
            userIDDAO.delete(userId);
        });
    }

}
