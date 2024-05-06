package com.example.project2.database;

import android.app.Application;
import android.util.Log;

import com.example.project2.MainActivity;
import com.example.project2.database.entities.Project_Items.Assignment;
import com.example.project2.database.entities.Project_Items.Item;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AssignmentRepository {

    private AssignmentDAO assignmentDAO;
    private ArrayList<Assignment> allAssignments;

    private static AssignmentRepository repository;

    private AssignmentRepository(Application application) {
        AssignmentDatabase db = AssignmentDatabase.getDatabase(application);
        this.assignmentDAO = db.AssignmentDAO();
        this.allAssignments = (ArrayList<Assignment>) this.assignmentDAO.getAllRecords();
    }

    public static AssignmentRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<AssignmentRepository> future = AssignmentDatabase.databaseWriteExecutor.submit(
                new Callable<AssignmentRepository>() {
                    @Override
                    public AssignmentRepository call() throws Exception {
                        return new AssignmentRepository(application);
                    }
                }
        );
        try {
            return future.get();
        }catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.ASSIGNMENT_TAG, "Problem getting AssignmentRepository, thread error");
        }
        return null;
    }

    public Assignment getAssignmentByAssignmentID(Integer id) {
        Future<Assignment> future = AssignmentDatabase.databaseWriteExecutor.submit(
                new Callable<Assignment>() {
                    @Override
                    public Assignment call() throws Exception {
                        return assignmentDAO.getAssignmentByAssignmentID(id);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.ASSIGNMENT_TAG, "Problem when getting assignment by assignment ID");
        }
        return null;
    }

    public ArrayList<Assignment> getAllAssignments() {
        Future<ArrayList<Assignment>> future = AssignmentDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Assignment>>() {
                    @Override
                    public ArrayList<Assignment> call() throws Exception {
                        return (ArrayList<Assignment>) assignmentDAO.getAllRecords();
                    }
                }
        );
        try{
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Log.i(MainActivity.ASSIGNMENT_TAG, "Problem when getting all assignments in the repository");
        }
        return null;
    }

    public Item getItemByItemName(String name) {
        Future<Item> future = AssignmentDatabase.databaseWriteExecutor.submit(
                new Callable<Item>() {
                    @Override
                    public Item call() throws Exception {
                        return assignmentDAO.getAssignmentByAssignmentName(name);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.ASSIGNMENT_TAG, "Problem when getting item by name");
        }
        return null;
    }

    public void insertAssignment(Assignment assignment) {
        AssignmentDatabase.databaseWriteExecutor.execute(() ->
        {
            assignmentDAO.insert(assignment);
        });
    }

}