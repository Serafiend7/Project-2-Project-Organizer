package com.example.project2.database.typeConverters;

import static java.lang.Long.parseLong;

import androidx.room.TypeConverter;

import com.example.project2.Project_Items.Announcement;
import com.example.project2.Project_Items.Assignment;
import com.example.project2.Project_Items.Item;
import com.example.project2.database.entities.UserID;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ProjectTypeConverters {
    @TypeConverter
    public long convertDateToLong(LocalDateTime date){
        ZonedDateTime zdt = ZonedDateTime.of(date, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }
    @TypeConverter
    public LocalDateTime convertLongToDate(Long epochMilli) {
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
    }

    @TypeConverter
    public String convertStringListToString(ArrayList<String> names) {
        StringBuilder s = new StringBuilder();
        for (String name : names) {
            s.append(name).append("`");
        }
        return s.toString();
    }

    @TypeConverter
    public ArrayList<String> convertStringToStringList(String s) {
        ArrayList<String> r = new ArrayList<>();
        String[] v = s.split("[`,]");
        for (int i = 0; i < v.length; i+=3) {
            r.add(v[i]);
        }
        return r;
    }


    @TypeConverter
    public String convertUserHashMapToString(HashMap<String,Boolean> users) {
        StringBuilder s = new StringBuilder();
        for (String user : users.keySet()) {
            s.append(user).append("`").append(users.get(user));
        }
        return s.toString();
    }

    @TypeConverter
    public HashMap<String,Boolean> convertStringToUserHashMap (String s) {
        HashMap<String,Boolean> userHashmap = new HashMap<>();
        String[] v = s.split("`");
        for (int i = 0; i < v.length; i+= 2) {
            String name = v[i];
            boolean completion;
            completion = Objects.equals(v[i+1], "true");
            userHashmap.put(name,completion);
        }
        return userHashmap;
    }

    @TypeConverter
    public String convertAnnouncementToString(Announcement a) {
        String s = "";
        s += a.getName() + "`" + convertStringListToString(a.getUsers()) + "`" +
                a.getMessage() + convertUserHashMapToString(a.getUsersViewed());
        return s;
    }

    @TypeConverter
    public Announcement convertStringToAnnouncement(String s) {
        int i = 0;
        String[]v = s.split("`");
        String name = v[i];
        i++;
        String userList = "";
        while(Objects.equals(v[i+1], "true") || Objects.equals(v[i+1], "false")) {
            userList += v[i] + "`" + v[i+1];
            i+=2;
        }
        ArrayList<String> users = convertStringToStringList(userList);
        String message = v[i];
        String hashMap = "";
        while (i < v.length) {
            hashMap += v[i] + "`" + v[i+1];
            i += 2;
        }
        HashMap<String,Boolean> map = convertStringToUserHashMap(hashMap);
        return new Announcement(name,users,message,map);
    }

    @TypeConverter
    public String convertAssignmentToString(Assignment a) {
        String s = "";
        s += a.getName() + "`" + convertStringListToString(a.getUsers()) + "`" +
                a.getAssignmentDetails() + "`" + convertUserHashMapToString(a.getCompletedUsers()) + "`" + convertDateToLong(a.getDueDate());
        return s;
    }

    @TypeConverter
    public Assignment convertStringToAssignment(String s) {
        int i = 0;
        String[] v = s.split("`");
        String name = v[i];
        i++;
        String userList = "";
        while (Objects.equals(v[i+1], "true") || Objects.equals(v[i+1], "false")) {
            userList += v[i] + "`" + v[i+1];
            i+=2;
        }
        ArrayList<String> users = convertStringToStringList(userList);
        String details = v[i];
        i++;
        String hashMap = "";
        while (i < v.length-1) {
            hashMap += v[i] + "`" + v[i+1];
            i += 2;
        }
        HashMap<String,Boolean> completion = convertStringToUserHashMap(hashMap);
        LocalDateTime dueDate = convertLongToDate(parseLong(v[i]));

        return new Assignment(name,users,details,completion,dueDate);

    }


    @TypeConverter
    public String convertAssignmentListToString(ArrayList<Assignment> assignments) {
        StringBuilder s = new StringBuilder();
        for (Assignment assignment : assignments) {
            s.append(convertAssignmentToString(assignment)).append("`");
        }
        return s.toString();
    }

    @TypeConverter
    public ArrayList<Assignment> convertStringToAssignmentList(String s) {
        int i = 0;
        ArrayList<Assignment> returns = new ArrayList<>();
        String[] v = s.split("`");
        while (i < v.length){
            String assignment = v[i] + "`";
            i++;
            while (Objects.equals(v[i+1], "true") || Objects.equals(v[i+1], "false")) {
                assignment += v[i] + "`" + v[i+1] + "`";
                i += 2;
            }
            assignment += v[i] + "`";
            i++;
            while (Objects.equals(v[i+1], "true") || Objects.equals(v[i+1], "false")) {
                assignment += v[i] + "`" + v[i+1] + "`";
                i += 2;
            }
            assignment += v[i] + "`";
            returns.add(convertStringToAssignment(assignment));
            i++;
        }

        return  returns;
    }

    @TypeConverter
    public String convertAnnouncementListToString(ArrayList<Announcement> announcements) {
        StringBuilder s = new StringBuilder();
        for (Announcement announcement : announcements) {
            s.append(convertAnnouncementToString(announcement)).append("`");
        }
        return s.toString();
    }

    @TypeConverter
    public ArrayList<Announcement> convertStringToAnnouncementList(String s){
        int i = 0;
        ArrayList<Announcement> returns = new ArrayList<>();
        String[] v = s.split("`");
        while (i < v.length){
            String announcement = v[i] + "`";
            i++;
            while (Objects.equals(v[i+1], "true") || Objects.equals(v[i+1], "false")) {
                announcement += v[i] + "`" + v[i+1] + "`";
                i += 2;
            }
            announcement += v[i] + "`";
            i++;
            while (Objects.equals(v[i+1], "true") || Objects.equals(v[i+1], "false")) {
                announcement += v[i] + "`" + v[i+1] + "`";
                i += 2;
            }
            returns.add(convertStringToAnnouncement(announcement));
        }

        return  returns;
    }

}
