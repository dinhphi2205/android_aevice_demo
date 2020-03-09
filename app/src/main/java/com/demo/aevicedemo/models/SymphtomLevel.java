package com.demo.aevicedemo.models;

public enum SymphtomLevel {
    MILD ("Mild", 1),
    MODERATE ("Moderate", 2),
    SERVERE ("Servere", 3);

    private String stringValue;
    private int intValue;
    private SymphtomLevel(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }
    @Override
    public String toString() {
        return stringValue;
    }

    public int intValue() {
        return intValue;
    }
}
