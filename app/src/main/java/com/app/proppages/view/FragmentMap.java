package com.app.proppages.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import com.app.proppages.view.model.FragmentModel;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilClass;

import java.util.ArrayList;

/**
 * Created by Joshua on 23/03/17.
 */
public class FragmentMap {

    private FragmentManager fManager;
    private ArrayList<FragmentModel> fragments;

    public FragmentMap ( FragmentManager fm ) {

        this.fManager = fm;
        this.fragments = new ArrayList<FragmentModel>();

    }

    /*
    * @method addFragment
    * @params FragmentModel fm
    * */
    public void addFragment ( FragmentModel fm ) {

        this.fragments.add(fm);

    }

    /*
    * @method replaceFragment
    * @params FragmentModel fm, boolean addToBackStack
    * */
    public void replaceFragment ( FragmentModel fm, boolean animate, boolean addToBackStack ) {

        // begin a new transaction
        FragmentTransaction replaceTransaction = this.fManager.beginTransaction();

        replaceTransaction.replace( fm.getLayout(), fm.getFragment() );
        if( addToBackStack ) {

            replaceTransaction.addToBackStack( fm.getFragmentLabel() );

        }

        if( animate ) {

            replaceTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );

        }

        replaceTransaction.commit();

    }

    /*
    * @method getFragment
    * @params String name
    * @return fragment or null
    * */
    public static Fragment getFragment ( String name ) {

        final Fragment fragment = (Fragment)UtilClass.getClassByName( name, UtilBase.PACKG_VIEW );

        if( fragment != null ) {

            return fragment;

        }

        return null;

    }

    /*
    * @method commit
    * */
    public void commit () {

        final ArrayList<FragmentModel> fgs = this.fragments;
        if(  fgs.size() > 0 ) {

            FragmentTransaction transaction = this.fManager.beginTransaction();
            for(FragmentModel fragment : fgs) {

                transaction.add( fragment.getLayout(), fragment.getFragment() );

            }

            transaction.commit();

        }

    }

}
