package xyz.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import xyz.pojo.Department;
import xyz.pojo.Employee;
import xyz.pojo.User;

/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
@Database(entities = {User.class, Employee.class, Department.class}, version =15)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract DepartmentDao departmentDao();

    public abstract EmployeeDao employeeDao();

    private static volatile AppDatabase INSTANCE;

    public static synchronized void init(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "db_xyz")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
    }

    public static AppDatabase getInstance() {
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
