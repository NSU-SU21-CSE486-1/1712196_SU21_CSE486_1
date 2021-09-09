package com.istiaksaif.Project03.roomdb.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.istiaksaif.Project03.Utils.Contact;
import com.istiaksaif.Project03.Utils.UniAff;
import com.istiaksaif.Project03.Utils.User;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insertUser(User... users);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UniAff uniAff);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert1(UniAff uniAff);
    @Insert
    void insertUniAff(UniAff... uniAffs);

    @Query("SELECT * FROM uniAffiliation WHERE UserId = :UserId")
    List<UniAff> findForUser(Integer UserId);

    @Query("SELECT * FROM contact WHERE UserId = :UserId")
    List<Contact> getUserContacts(Integer UserId);
    @Insert
    void insertContact(Contact... Contacts);

    @Delete
    void delete(User user);
}
