package com.istiaksaif.Project03.Utils;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "uniAffiliation")

public class UniAff{
    @PrimaryKey(autoGenerate = true)
    public int uniAffID;
    public String UniName;
    public String Id;
    public String Department;
    public String Level;
    public Integer UserId;

    public UniAff() {
    }

    public UniAff(int uniAffID, String UniName, String Id, String Department, String Level, Integer UserId) {
        this.uniAffID = uniAffID;
        this.UniName = UniName;
        this.Id = Id;
        this.Department = Department;
        this.Level = Level;
        this.UserId = UserId;
    }

    public int getUniAffID() {
        return uniAffID;
    }

    public String getUniName() {
        return UniName;
    }

    public String getId() {
        return Id;
    }

    public String getDepartment() {
        return Department;
    }

    public String getLevel() {
        return Level;
    }

    public void setUniAffID(int uniAffID) {
        this.uniAffID = uniAffID;
    }

    public void setUniName(String uniName) {
        UniName = uniName;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
