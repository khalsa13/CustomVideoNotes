package com.example.videonotesph1;

public class HomeModel {

    private String name;
    private String Image;
    private String video_ID;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getVideo_ID() {
        return video_ID;
    }

    public void setVideo_ID(String id) {
        this.video_ID = id;
    }


    public HomeModel(String name, String image,String id) {
        this.name = name;
        this.Image = image;
        this.video_ID = id;
    }
}
