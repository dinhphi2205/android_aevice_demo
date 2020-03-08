package com.demo.aevicedemo.models;

import com.demo.aevicedemo.utils.Utils;
enum SymphtonLevel {
    MILD ("Mild", 1),
    MODERATE ("Moderate", 2),
    SERVERE ("Servere", 3);

    private String stringValue;
    private int intValue;
    private SymphtonLevel(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }
    @Override
    public String toString() {
        return stringValue;
    }
}
public class Sympton {
    String date;
    int coughLevel;
    int wheezeLevel;
    String otherSymphotons;

    public Sympton(String date, int coughLevel, int wheezeLevel, String otherSymphotons) {
        this.date = date;
        this.coughLevel = coughLevel;
        this.wheezeLevel = wheezeLevel;
        this.otherSymphotons = otherSymphotons;
    }

    public Summary toSummary() {
        return new Summary(
                date,
                Utils.currentMiliseconToHHmm(),
                String.format("%s cough\n%s wheeze\n%s",
                        coughLevel == 1 ? SymphtonLevel.MILD.toString() : coughLevel == 2 ? SymphtonLevel.MODERATE.toString(): SymphtonLevel.SERVERE.toString(),
                        wheezeLevel == 1 ? SymphtonLevel.MILD.toString() : wheezeLevel == 2 ? SymphtonLevel.MODERATE.toString(): SymphtonLevel.SERVERE.toString(),
                        otherSymphotons),
                0);
    }
}
