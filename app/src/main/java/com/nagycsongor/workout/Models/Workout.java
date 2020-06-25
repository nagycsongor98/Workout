package com.nagycsongor.workout.Models;

import java.io.Serializable;
import java.util.Date;

public class Workout implements Serializable {
    private String name;
    private int burnedCalories;
    private String date;
    private int duration;
    private Date createdDate;
    private String imageURL;

    public Workout(){

    }

    public Workout(String name, int burnedCalories, String date, int duration, Date createdDate,String imageURL) {
        this.name = name;
        this.burnedCalories = burnedCalories;
        this.date = date;
        this.duration = duration;
        this.createdDate = createdDate;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
