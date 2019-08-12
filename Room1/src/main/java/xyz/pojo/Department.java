package xyz.pojo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;



/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */


@Entity
public class Department {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String dept;



    public Department(String dept) {
        this.dept = dept;
    }
    //省略了getter/setter方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

}

