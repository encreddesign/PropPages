package com.app.proppages.view;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.proppages.BaseActivity;
import com.app.proppages.R;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.model.ProfileModel;

/**
 * Created by Joshua on 22/04/17.
 */
public class ProfileSceneFragment extends Fragment {

    private TextView mProfileName;
    private ImageView mProfileImage;
    private Button mProfileCallButton;
    private Button mProfileEmailButton;

    private ProfileModel mProfileModel;

    public ProfileSceneFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_scene, container, false);

        this.mProfileName = (TextView) view.findViewById(R.id.profile_scene_name);
        this.mProfileImage = (ImageView) view.findViewById(R.id.profile_scene_image);
        this.mProfileCallButton = (Button) view.findViewById(R.id.profile_scene_call);
        this.mProfileEmailButton = (Button) view.findViewById(R.id.profile_scene_email);

        this.mProfileModel = (ProfileModel) getArguments().getSerializable("data");
        if( this.mProfileModel == null ) {
            Log.e(UtilBase.LOG_TAG, "ProfileFragment bundle passed as null");
        }

        this.mProfileName.setText( (this.mProfileModel.getValue("name") + " " + this.mProfileModel.getValue("lastname")) );

        Bitmap bitmap = BaseActivity.getICache().getFromMem(this.mProfileModel.getValue("image"));
        if(bitmap != null) {
            this.mProfileImage.setImageBitmap(bitmap);
        }

        return view;

    }

}
