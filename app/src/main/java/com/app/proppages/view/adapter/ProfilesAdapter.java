package com.app.proppages.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.proppages.R;
import com.app.proppages.view.model.ProfileModel;

import java.util.List;

/**
 * Created by Joshua on 15/04/17.
 */
public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ViewHolder> {

    private List<ProfileModel> dataSet;

    public ProfilesAdapter (List<ProfileModel> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // assign our values to view components
        holder.mNameView.setText("TestName");

    }

    // view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameView;
        public TextView mTeamView;
        public ImageView mImageView;

        public ViewHolder (View view) {
            super(view);

            mNameView = (TextView) view.findViewById(R.id.profile_prev_name);
            mTeamView = (TextView) view.findViewById(R.id.profile_prev_team);
            mImageView = (ImageView) view.findViewById(R.id.profile_prev_img);

        }

    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

}
