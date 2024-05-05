package com.example.project2.database;

import android.app.Application;
import android.util.Log;

import com.example.project2.database.entities.Project;
import com.example.project2.MainActivity;
import com.example.project2.database.entities.UserID;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProjectRepository {

    private ProjectDAO projectDAO;
    private ArrayList<Project> allProjects;

    private static ProjectRepository repository;

    private ProjectRepository(Application application) {
        ProjectDatabase db = ProjectDatabase.getDatabase(application);
        this.projectDAO = db.projectDAO();
        this.allProjects = (ArrayList<Project>) this.projectDAO.getAllRecords();
    }

    public static ProjectRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<ProjectRepository> future = ProjectDatabase.databaseWriteExecutor.submit(
                new Callable<ProjectRepository>() {
                    @Override
                    public ProjectRepository call() throws Exception {
                        return new ProjectRepository(application);
                    }
                }
        );
        try {
            return future.get();
        }catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.PROJECT_TAG, "Problem getting ProjectRepository, thread error");
        }
        return null;
    }

    public Project getProjectByProjectID(Integer id) {
        Future<Project> future = ProjectDatabase.databaseWriteExecutor.submit(
                new Callable<Project>() {
                    @Override
                    public Project call() throws Exception {
                        return projectDAO.getProjectByProjectID(id);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.TAG, "Problem when getting user by userID");
        }
        return null;
    }

    public ArrayList<Project> getAllProjects() {
        Future<ArrayList<Project>> future = ProjectDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Project>>() {
                    @Override
                    public ArrayList<Project> call() throws Exception {
                        return (ArrayList<Project>) projectDAO.getAllRecords();
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

//    public Project getProjectByProjectID(Integer id) {
//        Future<Project> future = ProjectDatabase.databaseWriteExecutor.submit(
//                new Callable<Project>() {
//                    @Override
//                    public Project call() throws Exception {
//                        return ProjectDAO.getProjectByProjectID(id);
//                    }
//                });
//        try {
//            return future.get();
//        } catch (InterruptedException | ExecutionException e) {
//            Log.i(MainActivity.TAG, "Problem when getting user by userID");
//        }
//        return null;
//    }

}
