package com.demo.aevicedemo.models;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "summary")
public class Summary {
    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "time")
    String time;

    @ColumnInfo(name = "description")
    String description;

    @PrimaryKey(autoGenerate = true)
    long id;

    public Summary(String date, String time, String description, long id) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.id = id;
    }
}
