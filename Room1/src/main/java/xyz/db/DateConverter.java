package xyz.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
