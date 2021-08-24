package com.istiaksaif.uniclubz.Model;

public class ClubJoinedListItem {
    private String image,clubName,status,clubId,admin;

    public ClubJoinedListItem() {
    }

    public ClubJoinedListItem(String image, String clubName, String status, String clubId, String admin) {
        this.image = image;
        this.clubName = clubName;
        this.status = status;
        this.clubId = clubId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
