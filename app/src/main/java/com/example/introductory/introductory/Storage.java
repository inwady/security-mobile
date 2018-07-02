package com.example.introductory.introductory;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Storage {
    private static String prefName = "main_data";
    private static Storage storage = new Storage();

    private SharedPreferences mainSharedPref;

    private void setMainSharedPref(SharedPreferences pref) {
        this.mainSharedPref = pref;
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor editor = mainSharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getData(String key) {
        String result = this.mainSharedPref.getString(key, null);
        if (result == null)
            return null;

        return result;
    }

    public static void initStorage(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Storage.prefName, MODE_PRIVATE);
        storage.setMainSharedPref(pref);
    }

    public static Storage getStorage() {
        return storage;
    }
}
