package com.app.proppages.view.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.proppages.BaseActivity;
import com.app.proppages.R;
import com.app.proppages.callbacks.recycler.RecyclerItemClickListener;
import com.app.proppages.view.model.ProfileModel;

import java.util.List;

/**
 * Created by Joshua on 15/04/17.
 */
public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ViewHolder> {

    private List<ProfileModel> dataSet;
    private RecyclerItemClickListener mRecyclerItemListener;

    public ProfilesAdapter (List<ProfileModel> dataSet, RecyclerItemClickListener clickListener) {

        this.dataSet = dataSet;
        this.mRecyclerItemListener = clickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerItemListener.OnItemClick(view, holder, holder.getPosition());
            }
        });

        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProfileModel model = this.dataSet.get(position);

        // assign our values to view components
        holder.mNameView.setText( (model.getValue("name") + " " + model.getValue("lastname")) );
        holder.mTeamView.setText(model.getValue("team"));

        holder.mRowLayout.setTag(model);

        Bitmap bitmap = BaseActivity.getICache().getFromMem(model.getValue("image"));
        if(bitmap != null) {
            holder.mImageView.setImageBitmap(bitmap);
        }

    }

    // view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameView;
        public TextView mTeamView;
        public ImageView mImageView;
        public RelativeLayout mRowLayout;

        public ViewHolder (View view) {
            super(view);

            mNameView = (TextView) view.findViewById(R.id.profile_prev_name);
            mTeamView = (TextView) view.findViewById(R.id.profile_prev_team);
            mImageView = (ImageView) view.findViewById(R.id.profile_prev_img);
            mRowLayout = (RelativeLayout) view.findViewById(R.id.profileRowLayout);

        }

    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

}
