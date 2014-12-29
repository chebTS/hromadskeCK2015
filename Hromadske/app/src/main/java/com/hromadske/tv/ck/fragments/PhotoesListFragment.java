package com.hromadske.tv.ck.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.activities.DetailActivity;
import com.hromadske.tv.ck.activities.MainActivity;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.hromadske.tv.ck.tasks.BaseHromTask;
import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 12/27/14.
 */
public class PhotoesListFragment extends BaseMenuFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = PhotoesListFragment.class.getSimpleName();
    private ListView listView;

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
        new BaseHromTask(getActivity(), view.findViewById(R.id.progress),
                SystemUtils.PHOTO_URL ,listView).execute();
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
}
