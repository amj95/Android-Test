package com.androidtest.santander.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidtest.santander.R;

public class SharedPreferenceUtils {

    private static final String SP_VERSION_KEY  = "SharedPrefsVersionKey";
    private static final int    DEFAULT_VERSION = 0;
    private static final int    SP_VERSION      = 4;

    private static SharedPreferenceUtils    instance;
    private static SharedPreferences sharedPreferences;

    private SharedPreferenceUtils(Context context) {

        sharedPreferences = context
                .getSharedPreferences(
                        context.getString(R.string.app_shared_prefs_key),
                        Context.MODE_PRIVATE
                );

        int version = sharedPreferences.getInt(SP_VERSION_KEY, DEFAULT_VERSION);



    }

    /**
     * Return its instance. It must receive the Application Context to retrieve and store
     * the information on entire application context
     *
     * @param context application {@link Context}
     * @return SharedPreferenceUtils
     */
    public synchronized static SharedPreferenceUtils instance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceUtils(context);
        }
        return instance;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void write(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String read(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    //Int value
    public void write(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int read(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void write(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float read(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    //Long value
    public void write(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long read(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    //Boolean value
    public void write(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean read(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
