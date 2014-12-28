package com.hromadske.tv.ck.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.activities.DetailActivity;
import com.hromadske.tv.ck.activities.MainActivity;
import com.hromadske.tv.ck.tasks.BaseHromTask;
import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 12/27/14.
 */
public class CultureListFragment extends BaseMenuFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = CultureListFragment.class.getSimpleName();
    private ListView listView;

    public static CultureListFragment newInstance(int sectionNumber) {
        CultureListFragment fragment = new CultureListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CultureListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        listView = (ListView)view.findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        BaseHromTask baseHromTask = new BaseHromTask(getActivity(), null, SystemUtils.CULTURE_URL ,listView);
        baseHromTask.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (((MainActivity)getActivity()).getTabletContainer() == null){
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            startActivity(intent);
        }else{
            //TODO
        }
    }
}
