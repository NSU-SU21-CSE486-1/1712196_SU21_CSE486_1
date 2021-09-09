package com.istiaksaif.Project03.Utils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "UserId")
    private Integer UserId;

    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "DOB")
    private String DOB;

    @ColumnInfo(name = "NID")
    private String NID;

    @ColumnInfo(name = "BloodGroup")
    private String BloodGroup;

    public User() {
    }

    public User(Integer UserId, String Name, String DOB, String NID, String BloodGroup) {
        this.UserId = UserId;
        this.Name = Name;
        this.DOB = DOB;
        this.NID = NID;
        this.BloodGroup = BloodGroup;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }
}
