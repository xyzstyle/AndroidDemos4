package xyz.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;
import java.util.List;

import xyz.pojo.User;


/**
 * Created by xyz on 2019/8/12.
 * Project Name:AndroidDemos4
 */
@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> getAll();


    @Query("SELECT * FROM user")
    Cursor getUserCursor();

    @Query("SELECT * FROM user WHERE _id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE name LIKE :name AND "
            + "password LIKE :password LIMIT 1")
    User findByName(String name, String password);


    @Query("SELECT * FROM user WHERE _id=:id")
    User getUserById(int id);

    @Insert
    void insert(User... users);

    @Insert
    void insert(User user);

    @Insert
    void insert(List<User> userLists);


    @Update
    void update(User... users);

    @Delete
    void delete(User user);


}
