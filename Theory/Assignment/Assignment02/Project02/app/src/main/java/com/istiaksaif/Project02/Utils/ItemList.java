package com.istiaksaif.Project02.Utils;

import android.widget.LinearLayout;

import java.io.Serializable;

public class ItemList implements Serializable {
    String phone,email,lOpen;

    public ItemList() {
    }

    public ItemList(String phone, String email, String lOpen) {
        this.phone = phone;
        this.email = email;
        this.lOpen = lOpen;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getlOpen() {
        return lOpen;
    }

    public void setlOpen(String lOpen) {
        this.lOpen = lOpen;
    }
}
