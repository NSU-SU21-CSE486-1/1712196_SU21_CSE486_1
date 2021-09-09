package com.istiaksaif.Project03.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.istiaksaif.Project03.Utils.Contact;
import com.istiaksaif.Project03.Utils.UniAff;
import com.istiaksaif.Project03.roomdb.Dao.Dao;
import com.istiaksaif.Project03.Utils.User;

@Database(entities = {User.class,UniAff.class, Contact.class}, version = 50)
public abstract class DatabaseClass extends RoomDatabase {

public abstract Dao userDao();
    private static DatabaseClass INSTANCE;

    public static DatabaseClass getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseClass.class, "DB_NAME")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}