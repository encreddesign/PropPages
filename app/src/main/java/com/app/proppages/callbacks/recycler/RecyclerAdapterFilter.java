package com.app.proppages.callbacks.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.proppages.BaseActivity;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 21/04/17.
 */
public class RecyclerAdapterFilter implements RecyclerFilterInterface {

    private RecyclerView mRecyclerView;

    private List<ProfileModel> mCopyData;
    private List<ProfileModel> mProfileData;

    private final String mFilterType = "name";

    RecyclerAdapterFilter ( RecyclerView view, List<ProfileModel> adapter ) {

        this.mRecyclerView = view;
        this.mProfileData = adapter;

        this.mCopyData = new ArrayList<ProfileModel>();
        this.mCopyData.addAll(this.mProfileData);

    }

    /*
    * @method isInModel
    * */
    private boolean isInModel ( CharSequence chars, ProfileModel model ) {

        if(model.getValue(this.mFilterType).toLowerCase().startsWith(chars.toString().toLowerCase())) {
            return true;
        }

        return false;

    }

    @Override
    public void onStartFilter(CharSequence chars) {}

    @Override
    public void onFilter(CharSequence chars) {

        BaseActivity.mWorkerThread.postTask(new FilterThread(chars));

    }

    /*
    * @class FilterThread
    * */
    private class FilterThread implements Runnable {

        CharSequence chars;

        public FilterThread (CharSequence chars) {
            this.chars = chars;
        }

        @Override
        public void run() {

            mProfileData.clear();

            if(chars.length() == 0) {
                mProfileData.addAll(mCopyData);
            } else {

                for(ProfileModel model : mCopyData) {
                    if(isInModel(chars, model)) {
                        mProfileData.add(0, model);
                    }
                }

            }

            BaseActivity.mUiHandler.post(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                }
            });

        }
    }

    /*
    * @method newInstance
    * */
    public static RecyclerAdapterFilter newInstance ( RecyclerView view, List<ProfileModel> adapter ) {
        return new RecyclerAdapterFilter( view, adapter );
    }

}
