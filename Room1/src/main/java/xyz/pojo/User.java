package xyz.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Date;


/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int _id;

    @ColumnInfo
    @NonNull
    private String name;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String address;

    @ColumnInfo
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public byte[] getHeadImage() {
        return headImage;
    }

    public void setHeadImage(byte[] headImage) {
        this.headImage = headImage;
    }

    @ColumnInfo(name = "head_image",typeAffinity = ColumnInfo.BLOB)
    private byte[] headImage;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", headImage=" + Arrays.toString(headImage) +
                '}';
    }
}
