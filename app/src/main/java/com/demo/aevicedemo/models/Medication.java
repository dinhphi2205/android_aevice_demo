package com.demo.aevicedemo.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medications")
public class Medication {
    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "medicine")
    String medicine;

    @ColumnInfo(name = "dose")
    int dose;

    @ColumnInfo(name = "frequent")
    int frequent;

    @ColumnInfo(name = "before")
    boolean before;

    @ColumnInfo(name = "time")
    String time;

    @ColumnInfo(name = "taken")
    boolean taken;

    @PrimaryKey(autoGenerate = true)
    long id;

    public Medication(String date, String medicine, int dose, int frequent, boolean before, String time, long id) {
        this.date = date;
        this.medicine = medicine;
        this.dose = dose;
        this.frequent = frequent;
        this.before = before;
        this.time = time;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getFrequent() {
        return frequent;
    }

    public void setFrequent(int frequent) {
        this.frequent = frequent;
    }

    public boolean isBefore() {
        return before;
    }

    public void setBefore(boolean before) {
        this.before = before;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Summary toSummary(String time) {
        return new Summary(date, time, String.format("%d tablet of %s taken", dose, medicine), 0);
    }

    // for recycler view header only
    public boolean isHeader() {
        return dose == 0 && frequent == 0;
    }
}
