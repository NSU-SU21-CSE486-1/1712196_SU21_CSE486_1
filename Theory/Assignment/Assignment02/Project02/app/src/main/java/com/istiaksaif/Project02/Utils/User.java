package com.istiaksaif.Project02.Utils;

import java.io.Serializable;

public class User implements Serializable {
    String Name,DOB,NID,BloodGroup;

    public User() {
    }

    public User(String Name, String DOB, String NID, String BloodGroup) {
        this.Name = Name;
        this.DOB = DOB;
        this.NID = NID;
        this.BloodGroup = BloodGroup;
    }
//    public String toString(){
//        return Name+" "+DOB+" "+" "+NID+" "+BloodGroup;
//    }

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
