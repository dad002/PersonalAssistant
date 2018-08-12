package com.example.kolvir.personalassistant.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends android.database.sqlite.SQLiteOpenHelper{

    private Context context;
    public static final Integer DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";

    public static final String TABLE_NAME = "DAY_BUSINESS";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(context, "start", Toast.LENGTH_LONG).show();
        Log.d("mLog", "onCreate Table");
        db.execSQL("create table " + TABLE_NAME + "(" + KEY_ID + " integer primary key, " + COLUMN_TITLE + " text," + COLUMN_DESCRIPTION + " text " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);

        onCreate(db);
    }
}
