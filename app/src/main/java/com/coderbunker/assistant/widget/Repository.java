package com.coderbunker.assistant.widget;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Repository {

    private static final String EXTRA_DATA = "EXTRA_DATA";

    private SharedPreferences preferences;

    public Repository(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveData(int widgetId, ArrayList<String> data) {
        preferences.edit()
                .putStringSet(EXTRA_DATA, new HashSet<>(data))
                .apply();
    }

    public ArrayList<String> getData() {
        Set<String> data = preferences.getStringSet(EXTRA_DATA, new HashSet<String>());
        return new ArrayList<>(data);
    }
}
