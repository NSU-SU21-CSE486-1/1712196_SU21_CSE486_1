package com.istiaksaif.uniclubz.Model;

public class ChatsList {
    String id,updatetime;

    public ChatsList() {
    }

    public ChatsList(String id, String updatetime) {
        this.id = id;
        this.updatetime = updatetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
