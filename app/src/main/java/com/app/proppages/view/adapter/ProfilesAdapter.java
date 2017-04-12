package com.app.proppages.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.proppages.R;
import com.app.proppages.http.ImageCache;
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

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView profileName = (TextView)rowView.findViewById(R.id.profile_prev_name);
        profileName.setText(
                this.values.get(position).getValue("name") + " " + this.values.get(position).getValue("lastname")
        );

        TextView profileTeam = (TextView)rowView.findViewById(R.id.profile_prev_team);
        profileTeam.setText(
                this.values.get(position).getValue("team")
        );

        ImageView profileImage = (ImageView)rowView.findViewById(R.id.profile_prev_img);
        if( ImageCache.newInstance().getFromMem(this.values.get(position).getValue("image")) != null ) {
            profileImage.setImageBitmap(
                    ImageCache.newInstance().getFromMem(this.values.get(position).getValue("image"))
            );
        }

        return rowView;
    }
}
