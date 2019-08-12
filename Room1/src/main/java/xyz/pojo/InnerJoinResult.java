package xyz.pojo;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
public class InnerJoinResult {

    private String name;

    @ColumnInfo(name="dep_id")
    private int depId;



    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
