package com.hromadske.tv.ck.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.activities.DetailActivity;
import com.hromadske.tv.ck.activities.MainActivity;
import com.hromadske.tv.ck.adapters.BaseEntitiesAdapter;
import com.hromadske.tv.ck.adapters.EntityCursorAdapter;
import com.hromadske.tv.ck.db.HromContentProvider;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.hromadske.tv.ck.tasks.EntitiesLoader;
import com.hromadske.tv.ck.utils.SystemUtils;
import com.rightutils.rightutils.collections.RightList;

import static com.hromadske.tv.ck.utils.SystemUtils.saveData;

/**
 * Created by cheb on 12/27/14.
 */
public class PhotoesListFragment extends BaseMenuFragment implements
        AdapterView.OnItemClickListener,
        LoaderManager.LoaderCallbacks<Object> {
    private static final String TAG = PhotoesListFragment.class.getSimpleName();
    private ListView listView;
    static final int LOADER_ID = 4;
    static final int CURSOR_LOADER_ID = 44;
    private View progressBar;
    private boolean isLoading = true;
    private EntityCursorAdapter entityCursorAdapter;

    public static PhotoesListFragment newInstance(int sectionNumber) {
        PhotoesListFragment fragment = new PhotoesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PhotoesListFragment() {

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
        if (SystemUtils.isOnline(getActivity())) {
            isLoading = true;
            Loader<RightList<BaseEntity>> loader = getLoaderManager().getLoader(LOADER_ID);
            loader.forceLoad();
            progressBar.setVisibility(View.VISIBLE);
        }else{
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
                EntitiesLoader loader = new EntitiesLoader(getActivity(), SystemUtils.PHOTOES_URL, progressBar);
                return loader;
            case CURSOR_LOADER_ID:
                Uri CONTENT_URI = HromContentProvider.PHOTOES_CONTENT_URI;
                return new CursorLoader(getActivity(), CONTENT_URI, null, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        progressBar.setVisibility(View.GONE);
        switch (loader.getId()){
            case LOADER_ID:
                RightList<BaseEntity> baseEntities = (RightList<BaseEntity>)data;
                listView.setAdapter(new BaseEntitiesAdapter(getActivity(), R.layout.item_entity, baseEntities));
                saveData(getActivity(), SystemUtils.PHOTOES_URL, baseEntities);
                break;
            case CURSOR_LOADER_ID:
                if (!isLoading) {
                    Cursor cursor = (Cursor) data;
                    entityCursorAdapter = new EntityCursorAdapter(getActivity(), cursor, 0);
                    listView.setAdapter(entityCursorAdapter);
                }
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
