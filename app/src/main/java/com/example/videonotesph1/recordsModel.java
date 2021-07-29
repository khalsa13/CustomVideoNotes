package com.example.videonotesph1;

public class recordsModel {
    private String path;
    private String time;
    private String subject;

    public recordsModel(String path, String time, String subject) {
        this.path = path;
        this.time = time;
        this.subject = subject;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
