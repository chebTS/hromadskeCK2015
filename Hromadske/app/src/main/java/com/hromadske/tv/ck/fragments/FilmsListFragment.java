package com.hromadske.tv.ck.fragments;

import android.os.Bundle;

import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 12/27/14.
 */
public class FilmsListFragment extends BaseListFragment {


    public static FilmsListFragment newInstance(int sectionNumber) {
        FilmsListFragment fragment = new FilmsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_URL, SystemUtils.FILMS_URL);
        fragment.setArguments(args);
        return fragment;
    }

    public FilmsListFragment() {

    }
}