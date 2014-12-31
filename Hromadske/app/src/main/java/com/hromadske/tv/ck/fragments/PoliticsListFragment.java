package com.hromadske.tv.ck.fragments;

import android.os.Bundle;

import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 12/27/14.
 */
public class PoliticsListFragment extends BaseListFragment {


    public static PoliticsListFragment newInstance(int sectionNumber) {
        PoliticsListFragment fragment = new PoliticsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_URL, SystemUtils.POLITICS_URL);
        fragment.setArguments(args);
        return fragment;
    }

    public PoliticsListFragment() {

    }
}
