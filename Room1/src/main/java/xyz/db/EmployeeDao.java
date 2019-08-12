package xyz.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import xyz.pojo.Employee;
import xyz.pojo.InnerJoinResult;


/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employee")
    List<Employee> getAllCompany();

    @Query("SELECT  name,dep_id  from employee INNER JOIN department ON Employee.dep_id=Department.id  Where Department.id=1")
    List<InnerJoinResult> getDepartmentFromCompany();

    @Insert
    void insert(List<Employee> employees);
}