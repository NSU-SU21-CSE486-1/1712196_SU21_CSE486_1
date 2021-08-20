package com.istiaksaif.Project02.Utils;

import java.io.Serializable;

public class FinalUniAffiliation implements Serializable {
    String UniName,Id,Department,Level,lOpen;

    public FinalUniAffiliation() {
    }

    public FinalUniAffiliation(String uniName, String id, String department, String level,String lOpen) {
        UniName = uniName;
        Id = id;
        Department = department;
        Level = level;
        this.lOpen = lOpen;
    }

    public String getlOpen() {
        return lOpen;
    }

    public void setlOpen(String lOpen) {
        this.lOpen = lOpen;
    }

    public String getUniName() {
        return UniName;
    }

    public void setUniName(String uniName) {
        UniName = uniName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }
}
