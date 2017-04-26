package com.app.proppages.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.proppages.BaseActivity;
import com.app.proppages.R;
import com.app.proppages.callbacks.OnListItemSelect;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.adapter.ProfilesAdapter;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 26/04/17.
 */
public class FavouritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mRecyclerLayout;
    private RecyclerView.Adapter mRecyclerAdapter;

    private List<ProfileModel> mProfiles;
    private OnListItemSelect listViewItemListener;

    private TextView mTitle;
    private final String mEmptyListString = "Your Favourites is Empty";

    public FavouritesFragment () {
        this.listViewItemListener = new OnListItemSelect();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        this.mProfiles = new ArrayList<ProfileModel>();

        this.mTitle = (TextView) view.findViewById(R.id.favourites_title);

        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.favourites_list);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.mRecyclerLayout = new LinearLayoutManager(view.getContext());
        this.mRecyclerView.setLayoutManager(this.mRecyclerLayout);

        mRecyclerAdapter = new ProfilesAdapter(this.mProfiles, this.listViewItemListener);
        this.mRecyclerView.setAdapter(mRecyclerAdapter);

        // run our sql query to fetch our profiles
        BaseActivity.mWorkerThread.postTask(new FavouritesQuery());

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.favourites, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_clear:
                // run delete query
                BaseActivity.mWorkerThread.postTask(new ClearFromDBTask());
                break;

            default:
                break;

        }

        return true;

    }

    /*
    * @class FavouritesQuery
    * */
    private final class FavouritesQuery implements Runnable {

        @Override
        public void run() {

            final List<ProfileModel> models = BaseActivity.getFavouritesMap().getAllProfiles();

            BaseActivity.mUiHandler.post(new Runnable() {
                @Override
                public void run() {

                    if(models == null) {
                        mTitle.setText(mEmptyListString);
                    } else {

                        mProfiles.addAll(models);
                        mRecyclerAdapter.notifyDataSetChanged();

                    }

                }
            });

        }
    }

    /*
    * @class ClearFromDBTask
    * */
    private final class ClearFromDBTask implements Runnable {

        @Override
        public void run() {

            if(BaseActivity.getFavouritesMap().getAllProfiles() != null) {

                BaseActivity.getFavouritesMap().deleteProfile(-1);

                BaseActivity.mUiHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        mTitle.setText(mEmptyListString);
                        mProfiles.clear();
                        mRecyclerAdapter.notifyDataSetChanged();

                        Toast.makeText(BaseActivity.getContext(), "Deleted Favourites", Toast.LENGTH_LONG).show();

                    }
                });

            }

        }
    }

}
