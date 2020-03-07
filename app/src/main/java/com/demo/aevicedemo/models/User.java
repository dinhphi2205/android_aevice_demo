package com.demo.aevicedemo.models;

public class User {
    String name;
    int yearsOld;
    String bloodType;
    String imageUrl;

    public User(String name,int yearsOld, String bloodType, String imageUrl) {
        this.name = name;
        this.yearsOld = yearsOld;
        this.bloodType = bloodType;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }
}
