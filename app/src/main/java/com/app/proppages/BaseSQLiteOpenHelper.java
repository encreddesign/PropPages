package com.app.proppages;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.proppages.db.FavouritesTable;

/**
 * Created by Joshua on 23/04/17.
 */
public class BaseSQLiteOpenHelper extends SQLiteOpenHelper {

    // database consts
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Favourites.db";

    // database sql
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + FavouritesTable.TABLE_NAME + " (" +
            FavouritesTable.COLUMN_PROFILE_ID + " INTEGER DEFAULT 0," +
            FavouritesTable.COLUMN_PROFILE_NAME + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_LASTNAME + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_TEAM + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_EMAIL + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_EXT + " INTEGER DEFAULT 0," +
            FavouritesTable.COLUMN_PROFILE_POSITION + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_NICKNAME + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_IMAGE + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_INITIALS + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_OFFICE + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_FILM + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_SONG + " TEXT NOT NULL," +
            FavouritesTable.COLUMN_PROFILE_HOLIDAY + " BOOLEAN DEFAULT FALSE" +
            ")";

    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + FavouritesTable.TABLE_NAME;

    public BaseSQLiteOpenHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_CREATE_TABLE);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
