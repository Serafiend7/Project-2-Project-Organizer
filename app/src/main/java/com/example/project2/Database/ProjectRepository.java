package com.example.project2.Database;

import android.app.Application;
import android.util.Log;

import com.example.project2.Database.entities.Project;
import com.example.project2.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProjectRepository {

    private ProjectDAO projectDAO;
    private ArrayList<Project> allProjects;

    public ProjectRepository(Application application) {
        ProjectDatabase db = ProjectDatabase.getDatabase(application);
        this.projectDAO = db.projectDAO();
        this.allProjects = this.projectDAO.getAllRecords();
    }

    public ArrayList<Project> getAllProjects() {
        Future<ArrayList<Project>> future = ProjectDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Project>>() {
                    @Override
                    public ArrayList<Project> call() throws Exception {
                        return projectDAO.getAllRecords();
                    }
                }
        );
        try{
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Log.i(MainActivity.PROJECT_TAG, "Problem when getting all projects in the repository");
        }
        return null;
    }

    public void insertProject(Project project) {
        ProjectDatabase.databaseWriteExecutor.execute(() ->
                {
                    projectDAO.insert(project);
                });
    }

}
