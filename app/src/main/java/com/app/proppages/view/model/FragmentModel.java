package com.app.proppages.view.model;

import android.app.Fragment;
import android.os.Bundle;

import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilClass;

import java.io.Serializable;

/**
 * Created by Joshua on 23/03/17.
 */
public class FragmentModel implements Serializable {

    private int layoutId;
    private String fragmentName;
    private Fragment layoutFragment;

    public FragmentModel () {

        // defaults
        this.layoutId = 0;
        this.layoutFragment = new Fragment();

    }

    /*
    * @method setModel
    * @params String name, int layoutId
    * @return this
    * */
    public FragmentModel setModel ( String name, int layoutId ) {

        this.layoutId = layoutId;
        this.fragmentName = name;

        try {

            Fragment fragment = (Fragment) UtilClass.getClassByName( name, UtilBase.PACKG_VIEW );

            if( fragment != null ) {

                this.layoutFragment = fragment;

            }

        } catch (ClassCastException ex) {
            throw new ClassCastException( "Unable to cast type Fragment to " + name );
        }

        return this;

    }

    /*
    * @method setData
    * @params Bundle data
    * */
    public FragmentModel setData ( Bundle data ) {

        this.layoutFragment.setArguments(data);
        return this;

    }

    /*
    * @method getLayout
    * @return int
    * */
    public int getLayout () {

        return this.layoutId;

    }

    /*
    * @method getFragmentLabel
    * @return String
    * */
    public String getFragmentLabel () {

        return this.fragmentName;

    }

    /*
    * @method setMFragment
    * @return fragment
    * */
    public Fragment getFragment () {

        return this.layoutFragment;

    }

}
