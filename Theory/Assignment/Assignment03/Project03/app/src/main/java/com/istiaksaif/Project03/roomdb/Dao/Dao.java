package com.istiaksaif.Project03.roomdb.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.istiaksaif.Project03.Utils.User;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insertUser(User... users);

    @Delete
    void delete(User user);
}
