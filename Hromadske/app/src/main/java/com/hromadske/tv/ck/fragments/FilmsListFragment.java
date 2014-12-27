package com.hromadske.tv.ck.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hromadske.tv.ck.R;

/**
 * Created by cheb on 12/27/14.
 */
public class FilmsListFragment extends BaseMenuFragment {

    public static FilmsListFragment newInstance(int sectionNumber) {
        FilmsListFragment fragment = new FilmsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FilmsListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }
}
