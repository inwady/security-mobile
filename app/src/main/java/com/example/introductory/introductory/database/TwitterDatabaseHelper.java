package com.example.introductory.introductory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TwitterDatabaseHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = TwitterDatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "twitter.db";
    private static final int DATABASE_VERSION = 1;

    public TwitterDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE twitter ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "twit TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
