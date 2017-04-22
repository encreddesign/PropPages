package com.app.proppages.callbacks;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.proppages.BaseActivity;
import com.app.proppages.R;
import com.app.proppages.callbacks.recycler.RecyclerItemClickListener;
import com.app.proppages.view.FragmentMap;
import com.app.proppages.view.adapter.ProfilesAdapter;
import com.app.proppages.view.model.FragmentModel;

import java.io.Serializable;

/**
 * Created by Joshua on 12/04/17.
 */
public class OnListItemSelect implements RecyclerItemClickListener {

    public OnListItemSelect () {}

    @Override
    public void OnItemClick(View v, ProfilesAdapter.ViewHolder holder, int position) {

        final Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) holder.mRowLayout.getTag());

        new FragmentMap(BaseActivity.getFManager())
                .replaceFragment(new FragmentModel().setModel("ProfileSceneFragment", R.id.base_frame).setData(bundle), true, true);

    }

}
