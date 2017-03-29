package com.app.proppages.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.app.proppages.view.model.ProfileModel;

import java.util.List;

/**
 * Created by Joshua on 26/03/17.
 */
public class ProfilesAdapter extends ArrayAdapter<ProfileModel> {

    // variables
    private Context context;
    private List<ProfileModel> values;

    public ProfilesAdapter ( Context context, List<ProfileModel> objects ) {
        super(context, -1, objects);

        this.context = context;
        this.values = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
