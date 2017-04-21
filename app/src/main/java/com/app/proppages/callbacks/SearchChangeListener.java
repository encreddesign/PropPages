package com.app.proppages.callbacks;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.app.proppages.callbacks.recycler.RecyclerAdapterFilter;
import com.app.proppages.view.model.ProfileModel;

import java.util.List;

/**
 * Created by Joshua on 21/04/17.
 */
public class SearchChangeListener implements TextWatcher {

    private RecyclerView mRecyclerView;
    private List<ProfileModel> mProfileData;
    private RecyclerAdapterFilter mRecyclerAdapterFilter;

    public SearchChangeListener ( RecyclerView view, List<ProfileModel> adapter ) {

        this.mRecyclerView = view;
        this.mProfileData = adapter;
        this.mRecyclerAdapterFilter = RecyclerAdapterFilter.newInstance( this.mRecyclerView, this.mProfileData );

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        if(charSequence.length() > 0) {

            // on text change, trigger filter method
            this.mRecyclerAdapterFilter.onFilter(charSequence);

        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        if(charSequence.length() > 0) {

            // on text change, trigger filter method
            this.mRecyclerAdapterFilter.onStartFilter(charSequence);

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {}

}
