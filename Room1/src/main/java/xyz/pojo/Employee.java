package xyz.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
@Entity(tableName = "employee",foreignKeys = @ForeignKey(entity = Department.class, parentColumns = "id", childColumns = "dep_id", onDelete = CASCADE))
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int age;
    private String address;
    private double salary;
    @ColumnInfo(name = "dep_id")
    @NonNull
    private int depId;








    public Employee(String name, int age, String address, double salary,int depId) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.depId=depId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }
}
