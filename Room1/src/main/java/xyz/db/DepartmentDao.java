package xyz.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import xyz.pojo.Department;
import xyz.pojo.InnerJoinResult;

/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
@Dao
public interface DepartmentDao {
    @Query("SELECT * FROM department")
    List<Department> getAllDepartment();
    //使用内连接查询


    @Insert
    void insert(List<Department> departments);
}
