package com.app.proppages.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.proppages.BaseSQLiteOpenHelper;
import com.app.proppages.view.model.ProfileModel;

/**
 * Created by Joshua on 23/04/17.
 */
public class FavouritesTable {

    // Table name
    public static final String TABLE_NAME = "FavouritesTable";

    // Table column names
    public static final String COLUMN_PROFILE_ID = "profileId";
    public static final String COLUMN_PROFILE_NAME = "profileName";
    public static final String COLUMN_PROFILE_LASTNAME = "profileLastname";
    public static final String COLUMN_PROFILE_TEAM = "profileTeam";
    public static final String COLUMN_PROFILE_EMAIL = "profileEmail";
    public static final String COLUMN_PROFILE_EXT = "profileExt";
    public static final String COLUMN_PROFILE_POSITION = "profilePosition";
    public static final String COLUMN_PROFILE_NICKNAME = "profileNickname";
    public static final String COLUMN_PROFILE_IMAGE = "profileImage";
    public static final String COLUMN_PROFILE_INITIALS = "profileInitials";
    public static final String COLUMN_PROFILE_OFFICE = "profileOffice";
    public static final String COLUMN_PROFILE_FILM = "profileFilm";
    public static final String COLUMN_PROFILE_SONG = "profileSong";
    public static final String COLUMN_PROFILE_HOLIDAY = "profileHoliday";

    private SQLiteDatabase database;

    public FavouritesTable (Context context) {
        this.database = new BaseSQLiteOpenHelper(context).getWritableDatabase();
    }

    /*
    * @method findProfileById
    * @params Integer profileId
    * */
    public Cursor findProfileById (int profileId) {

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PROFILE_ID + " =?";

        return this.database.rawQuery(query, new String[] { String.valueOf(profileId) });

    }

    /*
    * @method findProfileByName
    * @params String profileName
    * */
    public Cursor findProfileByName (String profileName) {

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PROFILE_NAME + " =?";

        return this.database.rawQuery(query, new String[] { profileName });

    }

    /*
    * @method getAllProfiles
    * */
    public Cursor getAllProfiles () {

        String query = "SELECT * FROM " + TABLE_NAME;

        return this.database.rawQuery(query, new String[] {});

    }

    /*
    * @method doInsert
    * */
    public void doInsert (ProfileModel model) {

        final ContentValues values = new ContentValues();

        values.put(COLUMN_PROFILE_ID, model.getIntValue("id"));
        values.put(COLUMN_PROFILE_NAME, model.getValue("name"));
        values.put(COLUMN_PROFILE_LASTNAME, model.getValue("lastname"));
        values.put(COLUMN_PROFILE_TEAM, model.getValue("team"));
        values.put(COLUMN_PROFILE_EMAIL, model.getValue("email"));
        values.put(COLUMN_PROFILE_EXT, model.getValue("ext"));
        values.put(COLUMN_PROFILE_POSITION, model.getValue("position"));
        values.put(COLUMN_PROFILE_NICKNAME, model.getValue("nicknames"));
        values.put(COLUMN_PROFILE_IMAGE, model.getValue("image"));
        values.put(COLUMN_PROFILE_INITIALS, model.getValue("initials"));
        values.put(COLUMN_PROFILE_OFFICE, model.getValue("office"));
        values.put(COLUMN_PROFILE_FILM, model.getValue("film"));
        values.put(COLUMN_PROFILE_SONG, model.getValue("song"));
        values.put(COLUMN_PROFILE_HOLIDAY, model.getBoolValue("holiday"));

        this.database.insert(TABLE_NAME, null, values);

    }

    /*
    * @method doUpdate
    * */
    public void doUpdate (ProfileModel model) {

        final ContentValues values = new ContentValues();

        values.put(COLUMN_PROFILE_NAME, model.getValue("name"));
        values.put(COLUMN_PROFILE_LASTNAME, model.getValue("lastname"));
        values.put(COLUMN_PROFILE_TEAM, model.getValue("team"));
        values.put(COLUMN_PROFILE_EMAIL, model.getValue("email"));
        values.put(COLUMN_PROFILE_EXT, model.getValue("ext"));
        values.put(COLUMN_PROFILE_POSITION, model.getValue("position"));
        values.put(COLUMN_PROFILE_NICKNAME, model.getValue("nickname"));
        values.put(COLUMN_PROFILE_IMAGE, model.getValue("image"));
        values.put(COLUMN_PROFILE_INITIALS, model.getValue("initials"));
        values.put(COLUMN_PROFILE_OFFICE, model.getValue("office"));
        values.put(COLUMN_PROFILE_FILM, model.getValue("film"));
        values.put(COLUMN_PROFILE_SONG, model.getValue("song"));
        values.put(COLUMN_PROFILE_HOLIDAY, model.getBoolValue("holiday"));

        final String query = COLUMN_PROFILE_ID + " =?";

        this.database.update(TABLE_NAME, values, query, new String[] { String.valueOf(model.getIntValue("id")) });

    }

    /*
    * @method doDelete
    * @params Integer profileId
    * */
    public void doDelete (int profileId) {

        String query = null;
        String values[] = null;

        if(profileId > -1) {

            query = COLUMN_PROFILE_ID + " =?";
            values = new String[]{ String.valueOf(profileId) };

        }

        this.database.delete(TABLE_NAME, query, values);

    }

    /*
    * @method close
    * */
    public void close () {
        this.database.close();
    }

}
