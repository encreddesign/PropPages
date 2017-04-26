package com.app.proppages.db;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua on 26/04/17.
 */
public class FavouritesMap {

    private Context mContext;
    private FavouritesTable mFavTable;

    FavouritesMap (Context context) {

        this.mContext = context;
        this.mFavTable = new FavouritesTable(context);

    }

    /*
    * @method addProfile
    * @params ProfileModel model
    * */
    public void addProfile (ProfileModel model) {

        this.mFavTable.doInsert(model);

        try {

            if(this.findProfileById(model.getIntValue("id")) != null) {
                Toast.makeText(this.mContext, "Profile Added To Favourites", Toast.LENGTH_LONG).show();
            } else {
                throw new Exception("Error adding profile");
            }

        } catch (Exception ex) {

            Log.e( UtilBase.LOG_TAG, "Database Error", ex );
            Toast.makeText(this.mContext, ex.getMessage(), Toast.LENGTH_LONG).show();

        }

    }

    /*
    * @method updateProfile
    * @params ProfileModel model
    * */
    public void updateProfile (ProfileModel model) {
        this.mFavTable.doUpdate(model);
    }

    /*
    * @method deleteProfile
    * @params Integer profileId
    * */
    public void deleteProfile (int profileId) {
        this.mFavTable.doDelete(profileId);
    }

    /*
    * @method findProfileById
    * @params Integer profileId
    * */
    @Nullable
    public ProfileModel findProfileById (int profileId) {

        Cursor cursor = this.mFavTable.findProfileById(profileId);

        if(!cursor.moveToFirst()) {

            cursor.close();
            return null;

        }

        return ProfileModel.newInstance(this.getFromCursor(cursor));

    }

    /*
    * @method findProfileByName
    * @params String profileName
    * */
    @Nullable
    public ProfileModel findProfileByName (String profileName) {

        Cursor cursor = this.mFavTable.findProfileByName(profileName);

        if(!cursor.moveToFirst()) {

            cursor.close();
            return null;

        }

        return ProfileModel.newInstance(this.getFromCursor(cursor));

    }

    /*
    * @method getAllProfiles
    * */
    @Nullable
    public List<ProfileModel> getAllProfiles () {

        Cursor cursor = this.mFavTable.getAllProfiles();

        if(!cursor.moveToFirst()) {

            cursor.close();
            return null;

        }

        List<ProfileModel> list = new ArrayList<ProfileModel>();

        do {
            list.add(ProfileModel.newInstance(this.getFromCursor(cursor)));
        } while (cursor.moveToNext());

        return list;

    }

    /*
    * @method getFromCursor
    * */
    private HashMap<String, String> getFromCursor (Cursor cursor) {

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("id", String.valueOf( cursor.getInt(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_ID)) ));
        map.put("name", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_NAME)));
        map.put("lastname", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_LASTNAME)));
        map.put("team", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_TEAM)));
        map.put("email", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_EMAIL)));
        map.put("ext", String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_EXT))));
        map.put("position", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_POSITION)));
        map.put("nicknames", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_NICKNAME)));
        map.put("image", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_IMAGE)));
        map.put("initials", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_INITIALS)));
        map.put("office", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_OFFICE)));
        map.put("film", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_FILM)));
        map.put("song", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_SONG)));
        map.put("holiday", cursor.getString(cursor.getColumnIndexOrThrow(FavouritesTable.COLUMN_PROFILE_HOLIDAY)));

        return map;

    }

    /*
    * @method close
    * */
    public void close () {
        this.mFavTable.close();
    }

    /*
    * @method newInstance
    * */
    public static FavouritesMap newInstance (Context context) {
        return new FavouritesMap(context);
    }

}
