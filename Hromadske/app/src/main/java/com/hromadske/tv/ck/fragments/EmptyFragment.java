package com.hromadske.tv.ck.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by cheb on 12/27/14.
 */
public  class EmptyFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static EmptyFragment newInstance(int sectionNumber) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public EmptyFragment() {
    }
}