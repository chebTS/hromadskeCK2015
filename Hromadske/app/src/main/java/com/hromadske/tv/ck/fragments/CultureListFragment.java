package com.hromadske.tv.ck.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.tasks.BaseHromTask;
import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 12/27/14.
 */
public class CultureListFragment extends BaseMenuFragment {
    private static final String TAG = CultureListFragment.class.getSimpleName();

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
        BaseHromTask baseHromTask = new BaseHromTask(getActivity(), null, SystemUtils.CULTURE_URL);
        baseHromTask.execute();
    }
}
