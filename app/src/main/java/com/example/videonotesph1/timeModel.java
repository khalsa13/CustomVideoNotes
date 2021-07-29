package com.example.videonotesph1;

public class timeModel {
    private String time;
    private String Image;

    public void setImage(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public timeModel(String time){
        this.time = time;
    }
    public timeModel(String time,String image){
        this.time = time;
        this.Image =image;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
