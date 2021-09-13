package com.istiaksaif.uniclubz.Model;

public class MemberItem {
    String name,image,uniname,userId,Department;

    public MemberItem() {
    }

    public MemberItem(String name, String image, String uniname, String userId, String department) {
        this.name = name;
        this.image = image;
        this.uniname = uniname;
        this.userId = userId;
        Department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUniname() {
        return uniname;
    }

    public void setUniname(String uniname) {
        this.uniname = uniname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
