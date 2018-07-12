package com.example.gauravsingh.myapplication.model;

import android.graphics.Bitmap;

public class CallHistory {

    private Bitmap image;
    private String name;
    private String number;
    private String calltime;
    private String callTimeInstance;
    private String callDate;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    public String getCallTimeInstance() {
        return callTimeInstance;
    }

    public void setCallTimeInstance(String callTimeInstance) {
        this.callTimeInstance = callTimeInstance;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }
}
