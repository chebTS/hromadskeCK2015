package com.hromadske.tv.ck.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
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
import com.hromadske.tv.ck.entities.BaseEntity;
import com.hromadske.tv.ck.tasks.EntitiesLoader;
import com.hromadske.tv.ck.utils.SystemUtils;
import com.rightutils.rightutils.collections.RightList;

import static com.hromadske.tv.ck.utils.SystemUtils.saveData;

/**
 * Created by cheb on 12/27/14.
 */
public class PhotoesListFragment extends BaseMenuFragment implements AdapterView.OnItemClickListener,
        LoaderManager.LoaderCallbacks<RightList<BaseEntity>> {
    private static final String TAG = PhotoesListFragment.class.getSimpleName();
    private ListView listView;
    static final int LOADER_ID = 4;
    private View progressBar;

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
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(SystemUtils.EXTRA_ENTITY, (BaseEntity)parent.getItemAtPosition(position));
        if (((MainActivity)getActivity()).getTabletContainer() == null){
            startActivity(intent);
        }else{
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(intent.getExtras());
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.tablet_container, detailFragment)
                    .commit();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
        if (SystemUtils.isOnline(getActivity())) {
            Loader<RightList<BaseEntity>> loader = getLoaderManager().getLoader(LOADER_ID);
            loader.forceLoad();
            progressBar.setVisibility(View.VISIBLE);
        }else{
            //TODO
        }
    }

    @Override
    public Loader<RightList<BaseEntity>> onCreateLoader(int id, Bundle args) {
        Loader<RightList<BaseEntity>> loader = new EntitiesLoader(getActivity(), SystemUtils.PHOTOES_URL, progressBar);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<RightList<BaseEntity>> loader, RightList<BaseEntity> data) {
        progressBar.setVisibility(View.GONE);
        listView.setAdapter(new BaseEntitiesAdapter(getActivity(), R.layout.item_entity, data));
        saveData(getActivity(), SystemUtils.PHOTOES_URL, data);
    }

    @Override
    public void onLoaderReset(Loader<RightList<BaseEntity>> loader) {

    }
}
