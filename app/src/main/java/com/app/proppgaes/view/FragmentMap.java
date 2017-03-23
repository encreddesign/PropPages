package com.app.proppgaes.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.app.proppgaes.utils.UtilBase;
import com.app.proppgaes.utils.UtilClass;
import com.app.proppgaes.view.model.FragmentModel;

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
    public void replaceFragment ( FragmentModel fm, boolean addToBackStack ) {

        // begin a new transaction
        FragmentTransaction replaceTransaction = this.fManager.beginTransaction();

        replaceTransaction.replace( fm.getLayout(), fm.getFragment() );
        if( addToBackStack ) {

            replaceTransaction.addToBackStack( fm.getFragmentLabel() );

        }

        replaceTransaction.commit();

    }

    /*
    * @method getFragment
    * @params String name
    * @return fragment or null
    * */
    public Fragment getFragment ( String name ) {

        final Fragment fragment = (Fragment)UtilClass.getClassByName( name, UtilBase.PACKG_VIEW );

        if( fragment.getView() != null ) {

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
