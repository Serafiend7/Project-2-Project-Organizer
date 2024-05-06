package com.example.project2.database;

import android.app.Application;
import android.util.Log;

import com.example.project2.MainActivity;
import com.example.project2.database.entities.Project_Items.Announcement;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AnnouncementRepository {
    private AnnouncementDAO announcementDAO;
    private ArrayList<Announcement> allAnnouncements;

    private static AnnouncementRepository repository;

    private AnnouncementRepository(Application application) {
        AnnouncementDatabase db = AnnouncementDatabase.getDatabase(application);
        this.announcementDAO = db.AnnouncementDAO();
        this.allAnnouncements = (ArrayList<Announcement>) this.announcementDAO.getAllRecords();
    }

    public static AnnouncementRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<AnnouncementRepository> future = AnnouncementDatabase.databaseWriteExecutor.submit(
                new Callable<AnnouncementRepository>() {
                    @Override
                    public AnnouncementRepository call() throws Exception {
                        return new AnnouncementRepository(application);
                    }
                }
        );
        try {
            return future.get();
        }catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.ANNOUNCEMENT_TAG, "Problem getting AnnouncementRepository, thread error");
        }
        return null;
    }

    public Announcement getAnnouncementByAnnouncementID(Integer id) {
        Future<Announcement> future = AnnouncementDatabase.databaseWriteExecutor.submit(
                new Callable<Announcement>() {
                    @Override
                    public Announcement call() throws Exception {
                        return announcementDAO.getAnnouncementByAnnouncementID(id);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.ANNOUNCEMENT_TAG, "Problem when getting announcement by announcement ID");
        }
        return null;
    }

    public ArrayList<Announcement> getAllAnnouncements() {
        Future<ArrayList<Announcement>> future = AnnouncementDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Announcement>>() {
                    @Override
                    public ArrayList<Announcement> call() throws Exception {
                        return (ArrayList<Announcement>) announcementDAO.getAllRecords();
                    }
                }
        );
        try{
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Log.i(MainActivity.ANNOUNCEMENT_TAG, "Problem when getting all announcements in the repository");
        }
        return null;
    }

    public Announcement getItemByItemName(String name) {
        Future<Announcement> future = AnnouncementDatabase.databaseWriteExecutor.submit(
                new Callable<Announcement>() {
                    @Override
                    public Announcement call() throws Exception {
                        return announcementDAO.getAnnouncementByAnnouncementName(name);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MainActivity.ANNOUNCEMENT_TAG, "Problem when getting announcement by name");
        }
        return null;
    }

    public void insertAnnouncement(Announcement announcement) {
        AnnouncementDatabase.databaseWriteExecutor.execute(() ->
        {
            announcementDAO.insert(announcement);
        });
    }
}
