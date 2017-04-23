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
    private TextView mProfileTeam;
    private TextView mProfilePosition;
    private TextView mProfileNickname;
    private TextView mProfileEmail;
    private TextView mProfileExtension;
    private TextView mProfileOffice;
    private TextView mProfileFilm;
    private TextView mProfileSong;
    private TextView mProfileHoliday;

    private ImageView mProfileImage;

    private Button mProfileCallButton;
    private Button mProfileEmailButton;

    private ProfileModel mProfileModel;

    public ProfileSceneFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_scene, container, false);

        this.mProfileName = (TextView) view.findViewById(R.id.profile_scene_name);
        this.mProfileTeam = (TextView) view.findViewById(R.id.profile_team_name);
        this.mProfilePosition = (TextView) view.findViewById(R.id.profile_team_position);

        this.mProfileNickname = (TextView) view.findViewById(R.id.profile_scene_nickname);
        this.mProfileEmail = (TextView) view.findViewById(R.id.profile_scene_emailText);
        this.mProfileExtension = (TextView) view.findViewById(R.id.profile_scene_callText);
        this.mProfileOffice = (TextView) view.findViewById(R.id.profile_scene_office);
        this.mProfileFilm = (TextView) view.findViewById(R.id.profile_scene_film);
        this.mProfileSong = (TextView) view.findViewById(R.id.profile_scene_song);
        this.mProfileHoliday = (TextView) view.findViewById(R.id.profile_scene_holiday);

        this.mProfileImage = (ImageView) view.findViewById(R.id.profile_scene_image);

        this.mProfileCallButton = (Button) view.findViewById(R.id.profile_scene_call);
        this.mProfileEmailButton = (Button) view.findViewById(R.id.profile_scene_email);

        this.mProfileModel = (ProfileModel) getArguments().getSerializable("data");
        if( this.mProfileModel == null ) {
            Log.e(UtilBase.LOG_TAG, "ProfileFragment bundle passed as null");
        }

        this.mProfileName.setText( (this.mProfileModel.getValue("name") + " " + this.mProfileModel.getValue("lastname")) );
        this.mProfileTeam.setText(this.mProfileModel.getValue("team"));
        this.mProfilePosition.setText(this.mProfileModel.getValue("position"));

        if( this.isHoliday() ) {
            this.mProfileHoliday.setVisibility(View.VISIBLE);
            this.mProfileCallButton.setEnabled(false);
        }

        this.mProfileNickname.setText( ("Nickname: " + this.mProfileModel.getValue("nicknames")) );
        this.mProfileEmail.setText( ("Email: " + this.mProfileModel.getValue("email")) );
        this.mProfileExtension.setText( ("Extension: " + this.mProfileModel.getValue("ext")) );
        this.mProfileOffice.setText( ("Office: " + this.mProfileModel.getValue("office")) );
        this.mProfileFilm.setText( ("Favourite Film: " + this.mProfileModel.getValue("film")) );
        this.mProfileSong.setText( ("Favourite Song: " + this.mProfileModel.getValue("song")) );

        Bitmap bitmap = BaseActivity.getICache().getFromMem(this.mProfileModel.getValue("image"));
        if(bitmap != null) {
            this.mProfileImage.setImageBitmap(bitmap);
        }

        return view;

    }

    /*
    * @method isHoliday
    * */
    private boolean isHoliday () {

        if( this.mProfileModel.getBoolValue("holiday") ) {
            return true;
        }

        return false;

    }

}
