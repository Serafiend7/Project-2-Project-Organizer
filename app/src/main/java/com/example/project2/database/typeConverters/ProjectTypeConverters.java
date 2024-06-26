package com.example.project2.database.typeConverters;

import static java.lang.Long.parseLong;

import androidx.room.TypeConverter;

import com.example.project2.database.entities.Project_Items.Announcement;
import com.example.project2.database.entities.Project_Items.Assignment;

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
    public String convertIntegerListToString(ArrayList<Integer> ids) {
        StringBuilder s = new StringBuilder();
        for (Integer id : ids) {
            s.append(id).append("`");
        }
        return s.toString();
    }

    @TypeConverter
    public ArrayList<Integer> convertStringToIntegerList(String s) {
        ArrayList<Integer> r = new ArrayList<>();
        String[] v = s.split("[`,]");
        for (int i = 0; i < v.length; i+=3) {
            r.add(Integer.valueOf(v[i]));
        }
        return r;
    }

}
