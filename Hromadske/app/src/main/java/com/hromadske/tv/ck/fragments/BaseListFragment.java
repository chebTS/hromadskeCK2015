package com.hromadske.tv.ck.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.activities.DetailActivity;
import com.hromadske.tv.ck.activities.MainActivity;
import com.hromadske.tv.ck.adapters.EntitiesAdapter;
import com.hromadske.tv.ck.adapters.EntityCursorAdapter;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.hromadske.tv.ck.tasks.EntitiesLoader;
import com.hromadske.tv.ck.utils.SystemUtils;
import com.rightutils.rightutils.collections.RightList;

import static com.hromadske.tv.ck.utils.SystemUtils.saveData;

/**
 * Created by cheb on 31.12.2014.
 */
public class BaseListFragment extends BaseMenuFragment  implements
        AdapterView.OnItemClickListener,
        LoaderManager.LoaderCallbacks<Object>{
    private static final String TAG = BaseListFragment.class.getSimpleName();
    protected static final String ARG_URL = "server_url";
    static final int LOADER_ID = 1;
    static final int CURSOR_LOADER_ID = 11;
    protected ListView listView;
    protected View progressBar;
    protected boolean isLoading = true;
    protected EntityCursorAdapter entityCursorAdapter;
    private String url;
    private Uri content_uri;

    public BaseListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(ARG_URL);
        content_uri = SystemUtils.getURIbyURL(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView)view.findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        progressBar = view.findViewById(R.id.progress);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isLoading) {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(SystemUtils.EXTRA_ENTITY, (BaseEntity) parent.getItemAtPosition(position));
            if (((MainActivity) getActivity()).getTabletContainer() == null) {
                startActivity(intent);
            } else {
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(intent.getExtras());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.tablet_container, detailFragment)
                        .commit();
            }
        }else{
            Cursor cursor = entityCursorAdapter.getCursor();
            cursor.moveToPosition(position);
            BaseEntity entity = SystemUtils.buildEntity(cursor);
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(SystemUtils.EXTRA_ENTITY, entity);
            if (((MainActivity) getActivity()).getTabletContainer() == null) {
                startActivity(intent);
            } else {
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(intent.getExtras());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.tablet_container, detailFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
        if (SystemUtils.isOnline(getActivity()) && !SystemUtils.getUpdateStatusByURL(url)) {
            isLoading = true;
            Loader<RightList<BaseEntity>> loader = getLoaderManager().getLoader(LOADER_ID);
            loader.forceLoad();
            progressBar.setVisibility(View.VISIBLE);
        }else{
            Log.i(TAG, "Restoring.. " + url);
            isLoading = false;
            Loader<Cursor> cursorLoader = getLoaderManager().getLoader(CURSOR_LOADER_ID);
            cursorLoader.forceLoad();
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id){
            case LOADER_ID:
                EntitiesLoader loader = new EntitiesLoader(getActivity(), url, progressBar);
                return loader;
            case CURSOR_LOADER_ID:
                return new CursorLoader(getActivity(), content_uri, null, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

        switch (loader.getId()){
            case LOADER_ID:
                RightList<BaseEntity> baseEntities = (RightList<BaseEntity>)data;
                listView.setAdapter(new EntitiesAdapter(getActivity(), R.layout.item_entity, baseEntities));
                saveData(getActivity(), url, baseEntities);
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case CURSOR_LOADER_ID:
                if (!isLoading) {
                    Cursor cursor = (Cursor) data;
                    entityCursorAdapter = new EntityCursorAdapter(getActivity(), cursor, 0);
                    listView.setAdapter(entityCursorAdapter);
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
