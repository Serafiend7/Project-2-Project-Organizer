package com.example.project2.database.typeConverters;

import androidx.room.TypeConverter;

import com.example.project2.Project_Items.Announcement;
import com.example.project2.database.entities.UserID;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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

//    @TypeConverter
//    public String convertAnnouncementToString(Announcement a) {
//        String s = "";
//        s += a.getName()
//    }


}
