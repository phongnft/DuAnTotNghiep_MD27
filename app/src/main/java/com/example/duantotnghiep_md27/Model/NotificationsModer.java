package com.example.duantotnghiep_md27.Model;

public class NotificationsModer {
    String  textNotifications;
    int imageNotifications;

    public String getTextNotifications() {
        return textNotifications;
    }

    public void setTextNotifications(String textNotifications) {
        this.textNotifications = textNotifications;
    }

    public int getImageNotifications() {
        return imageNotifications;
    }

    public void setImageNotifications(int imageNotifications) {
        this.imageNotifications = imageNotifications;
    }

    public NotificationsModer(String textNotifications, int imageNotifications) {
        this.textNotifications = textNotifications;
        this.imageNotifications = imageNotifications;
    }


}
