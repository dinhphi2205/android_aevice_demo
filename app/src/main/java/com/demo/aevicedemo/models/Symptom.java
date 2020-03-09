package com.demo.aevicedemo.models;

import com.demo.aevicedemo.utils.Utils;

public class Symptom {
    String date;
    int coughLevel;
    int wheezeLevel;
    String otherSymphotons;

    public Symptom(String date, int coughLevel, int wheezeLevel, String otherSymphotons) {
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
                        coughLevel == 1 ? SymphtomLevel.MILD.toString() : coughLevel == 2 ? SymphtomLevel.MODERATE.toString(): SymphtomLevel.SERVERE.toString(),
                        wheezeLevel == 1 ? SymphtomLevel.MILD.toString() : wheezeLevel == 2 ? SymphtomLevel.MODERATE.toString(): SymphtomLevel.SERVERE.toString(),
                        otherSymphotons),
                0);
    }
}
