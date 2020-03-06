package com.demo.aevicedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.util.Set;

public class Prefs {
    static Prefs singleton = null;
    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    static String PREFS_TAG = "prefs_tag";

    Prefs(Context context) {
        preferences = context.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static Prefs with(Context context) {
        if (singleton == null) {
            singleton = new Builder(context).build();
        }
        return singleton;
    }

    public void save(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public void save(String key, String value) {
        editor.putString(key, value).apply();
    }

    public void save(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public void save(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public void save(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public void save(String key, Set<String> value) {
        editor.putStringSet(key, value).apply();
    }

    public void save(String key, byte[] bytes) {
        String value = Base64.encodeToString(bytes, Base64.NO_WRAP);
        editor.putString(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        try {
            return preferences.getInt(key, defValue);
        } catch (ClassCastException e) {
            return defValue;
        }
    }

    public boolean contains(String key) {
        return preferences.contains(key);
    }

    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    public long getLong(String key, long defValue) {
        try {
            return preferences.getLong(key, defValue);
        } catch (ClassCastException e) {
            return defValue;
        }
    }

    private static class Builder {

        private final Context context;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        /**
         * Method that creates an instance of Prefs
         *
         * @return an instance of Prefs
         */
        public Prefs build() {
            return new Prefs(context);
        }
    }
}
