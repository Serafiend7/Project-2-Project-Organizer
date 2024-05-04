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
    public String convertUserIDToString(UserID user) {
        String string = "";
        string += user.getUsername() + "," + user.getPassword() + "," + user.isAdmin();
        return string;
    }
    @TypeConverter
    public UserID convertStringToUserID(String s) {
        String[] v = s.split(",");
        boolean admin;
        admin = Objects.equals(v[2], "true");
        return new UserID(v[0],v[1],admin);
    }

    @TypeConverter
    public String convertUserListToString(ArrayList<UserID> users) {
        StringBuilder s = new StringBuilder();
        for (UserID user : users) {
            s.append(convertUserIDToString(user)).append(",");
        }
        return s.toString();
    }

    @TypeConverter
    public ArrayList<UserID> convertStringToUserList(String s) {
        ArrayList<UserID> r = new ArrayList<UserID>();
        String[] v = s.split(",");
        for (int i = 0; i < v.length; i+=3) {
            r.add(new UserID(v[i],v[i+1],v[i+2].equals("true")));
        }
        return r;
    }


    @TypeConverter
    public String convertUserHashMapToString(HashMap<UserID,Boolean> users) {
        StringBuilder s = new StringBuilder();
        for (UserID user : users.keySet()) {
            s.append(convertUserIDToString(user)).append(",").append(users.get(user));
        }
        return s.toString();
    }

    @TypeConverter
    public HashMap<UserID,Boolean> convertStringToUserHashMap (String s) {
        HashMap<UserID,Boolean> userHashmap = new HashMap<>();
        String[] v = s.split(",");
        for (int i = 0; i < v.length; i+= 4) {
            String u = v[i] + "," + v[i+1] + "," + v[i+2];
            UserID user = convertStringToUserID(u);
            boolean completion;
            completion = Objects.equals(v[i+3], "true");
            userHashmap.put(user,completion);
        }
        return userHashmap;
    }

    @TypeConverter
    public String convertAnnouncementToString(Announcement a) {
        String s = "";
        s += "Announcement," + a.getName() + "," + convertUserListToString(a.getUsers()) + "," + a.getMessage();
        return s;
    }

    @TypeConverter
    public Announcement convertStringToAnnouncement(String s) {
        String[]v = s.split(",");
        String name = v[0];
        String userList = "";
        for (int i = 1; i < v.length-1; i++) {
            userList += v[i] + ",";
        }
        ArrayList<UserID> users = convertStringToUserList(userList);
        String message = v[v.length-1];
        return new Announcement(name,users,message);
    }

    @TypeConverter
    public String convertAssignmentToString(Assignment a) {
        String s = "";
        s += "Assignment," + a.getName() + "," + convertUserListToString(a.getUsers()) + "," + a.getAssignmentDetails() + "," +
                convertUserHashMapToString(a.getCompletedUsers()) + "," + convertDateToLong(a.getDueDate());
        return s;
    }

    @TypeConverter
    public Assignment convertStringToAssignment(String s) {
        String[] v = s.split(",");
        String name = v[0];
        String userList = "";
        int i;
        for (i = 1; Objects.equals(v[i+2], "true") || Objects.equals(v[i+2], "false"); i += 3) {
            userList += v[i] + "," + v[i+1] + "," + v[i+2] + ",";
        }
        ArrayList<UserID> users = convertStringToUserList(userList);
        String details = v[i+2];
        String hashMap = "";
        i++;
        while (Objects.equals(v[i+1], "true") || Objects.equals(v[i+1], "false")) {
            hashMap += v[i] + "," + v[i+1];
            i += 2;
        }
        HashMap<UserID,Boolean> completion = convertStringToUserHashMap(hashMap);
        LocalDateTime dueDate = convertLongToDate(parseLong(v[i]));

        return new Assignment(name,users,details,completion,dueDate);

    }

    @TypeConverter
    public String convertItemListToString(ArrayList<Item> items) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getClass() == Assignment.class) {
                Assignment a = (Assignment) items.get(i);
                s.append(convertAssignmentToString(a));
            }
            else if (items.get(i).getClass() == Announcement.class) {
                Announcement a = (Announcement) items.get(i);
                s.append(convertAnnouncementToString(a));
            }

            s.append(",");
        }

        return s.toString();
    }

    @TypeConverter
    public ArrayList<Item> convertStringToItemList(String s) {
        ArrayList<Item> returns = new ArrayList<>();
        String[] v = s.split(",");
        int i = 0;
        while (i < v.length) {
            if (Objects.equals(v[i], "Assignment")) {
                String a = "";
                i++;
                a+= v[i];
                i++;
                while (Objects.equals(v[i+2], "true") || Objects.equals(v[i+2], "false")) {
                    a += v[i] + "," + v[i+1] + "," + v[i+2] + ",";
                    i += 3;
                }
                a+= v[i] + ",";
                i++;
                while (Objects.equals(v[i+1], "true") || Objects.equals(v[i+1], "false")) {
                    a += v[i] + "," + v[i+1] + ",";
                    i += 2;
                }
                a+= v[i] + ",";
                returns.add(convertStringToAssignment(a));
                i++;
            }
            else if (Objects.equals(v[i], "Announcement")) {
                String a = "";
                i++;
                a += v[i] + ",";
                i++;
                while (Objects.equals(v[i+2], "true") || Objects.equals(v[i+2], "false")) {
                    a += v[i] + "," + v[i+1] + "," + v[i+2] + ",";
                    i += 3;
                }
                a+= v[i] + ",";
                returns.add(convertStringToAnnouncement(a));
                i++;
            }

        }
        return returns;
    }

}
