package com.istiaksaif.Project03.Utils;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int conId;
    private String Email;
    private String Phone;
    private Integer UserId;

    public Contact() {
    }

    public Contact(int conId, String Email, String Phone, Integer UserId) {
        this.conId = conId;
        this.Email = Email;
        this.Phone = Phone;
        this.UserId = UserId;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
