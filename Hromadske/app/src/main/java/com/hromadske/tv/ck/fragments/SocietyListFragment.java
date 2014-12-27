package com.hromadske.tv.ck.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hromadske.tv.ck.R;

/**
 * Created by cheb on 12/27/14.
 */
public class SocietyListFragment extends BaseMenuFragment {

    public static SocietyListFragment newInstance(int sectionNumber) {
        SocietyListFragment fragment = new SocietyListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SocietyListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }
}
