package com.hromadske.tv.ck.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hromadske.tv.ck.R;

/**
 * Created by cheb on 12/27/14.
 */
public class PoliticsListFragment extends BaseMenuFragment {

    public static PoliticsListFragment newInstance(int sectionNumber) {
        PoliticsListFragment fragment = new PoliticsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PoliticsListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }
}
